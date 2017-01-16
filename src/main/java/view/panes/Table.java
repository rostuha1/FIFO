package view.panes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Table extends GridPane {

    public static final Table instance = new Table();
    private int rows;
    private int columns;

    private ArrayList<Element> toReset = new ArrayList<>();
    private ArrayList<ArrayList<Element>> elements = new ArrayList<>();
    private ArrayList<Element> headers = new ArrayList<>();

    private Table() {
        setAlignment(Pos.CENTER);

        setMaxSize(0, 0);
        resize(10, 5);
        generate();
    }

    public void resize(int columns, int rows) {

        headers.forEach(getChildren()::remove);
        elements.forEach(el -> {
            el.forEach(element -> getChildren().remove(element));
            el.clear();
        });

        this.columns = columns;
        this.rows = rows;

        init();
    }

    private void init() {
        for (int i = 0; i < columns; i++) {
            elements.add(new ArrayList<>());
            for (int j = 0; j < rows; j++) {
                Element element = createElement();
                elements.get(i).add(element);
                GridPane.setMargin(element, Settings.fullInsets);
                add(element, i, j + 1);
            }

            Element element = createElement();
            headers.add(element);
            GridPane.setMargin(element, Settings.fullInsetsBottom);
            add(element, i, 0);

        }

    }

    private Element createElement() {
        Element element = new Element();
        element.setMinSize(40, 40);
        element.setStyle(Settings.defaultElementStyle);
        element.setFont(Settings.textFont);

        return element;
    }

    public void addColumn() {
        Element el = createElement();
        GridPane.setMargin(el, Settings.fullInsetsBottom);
        headers.add(el);
        add(el, columns, 0);

        ArrayList<Element> newElements = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            Element element = createElement();
            GridPane.setMargin(element, Settings.fullInsets);
            newElements.add(element);
            add(element, columns, i + 1);
        }

        columns++;
        elements.add(newElements);
    }

    public void addRow() {
        rows++;
        for (int i = 0; i < columns; i++) {
            Element element = createElement();
            GridPane.setMargin(element, Settings.fullInsets);
            add(element, i, rows);
            elements.get(i).add(element);
        }
    }

    public void removeColumn() {
        columns--;

        getChildren().remove(headers.get(columns));
        headers.remove(headers.get(columns));

        elements.get(columns).forEach(getChildren()::remove);
        elements.remove(elements.get(columns));
    }

    public void removeRow() {
        rows--;

        elements.forEach(element -> {
            Element last = element.get(rows);
            getChildren().remove(last);
            element.remove(last);
        });

    }

    public void generate() {
        headers.forEach(header -> header.setNumber((int) (Math.random() * 10)));
    }

    public void emulate() {
        toReset.forEach(element -> element.setStyle(Settings.defaultElementStyle));
        toReset.clear();

        elements.forEach(col -> col.forEach(el -> el.setNumber(-1)));

        for (int i = 0; i < columns; i++) {
            int pointer = headers.get(i).number;
            if (!push(i, pointer)) {
                Element el = headers.get(i);
                el.setStyle(Settings.elementStyle);
                toReset.add(el);
            }

            if (i < columns - 1) {
                copyPagesToNext(i);
            }

        }
    }

    private void copyPagesToNext(int column) {
        ArrayList<Element> currentPages = elements.get(column);
        ArrayList<Element> nextPages = elements.get(column + 1);

        for (int i = 0; i < rows; i++) {
            int nextNumber = currentPages.get(i).number;
            if (nextNumber != -1) nextPages.get(i).setNumber(nextNumber);
        }
    }

    private boolean push(int column, int number) {
        ArrayList<Element> pages = elements.get(column);

        for (Element page : pages) {
            if (page.number == number) {
                return true;
            }
            if (page.number == -1) {
                shift(pages, number);
                return false;
            }
        }

        shift(pages, number);
        return false;
    }

    private void addFirst(ArrayList<Element> pages, int number) {
        for (int i = 1; i < pages.size(); i++) {
            pages.get(i).setNumber(pages.get(i + 1).number);
        }
        pages.get(0).setNumber(number);
    }

    private void shift(ArrayList<Element> pages, int number) {
        for (int i = pages.size() - 1; i > 0; i--) {
            pages.get(i).setNumber(pages.get(i - 1).number);
        }
        pages.get(0).setNumber(number);
    }

    private class Element extends Label {
        private int number = -1;

        {
            setAlignment(Pos.CENTER);
            setFont(Settings.textFont);
            setTextFill(Color.YELLOW);
        }

        public void setNumber(int number) {
            setText(number == -1 ? "" : String.valueOf(number));
            this.number = number;
        }
    }

}
