import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Stage;

public class ComboTableStyleWarning extends Application {

    public enum Day { математика, русский_язык, биология, история, обществознание, этикет, урок}

    private final ObservableList<WorkDay> data = FXCollections.observableArrayList(
            new WorkDay(Day.урок),
            new WorkDay(Day.урок),
            new WorkDay(Day.урок),
            new WorkDay(Day.урок),
            new WorkDay(Day.урок)

    );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        TableColumn<WorkDay, Day> dayCol = new TableColumn<>("8а класс");
        TableColumn<WorkDay, Day> dayCol2 = new TableColumn<>("9а класс");
        dayCol.setMinWidth(100);
        dayCol2.setMinWidth(100);
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        dayCol.setCellFactory(ComboBoxTableCell.<WorkDay, Day>forTableColumn(Day.values()));
        dayCol.setEditable(true);

        dayCol2.setCellValueFactory(new PropertyValueFactory<>("day"));
        dayCol2.setCellFactory(ComboBoxTableCell.<WorkDay, Day>forTableColumn(Day.values()));
        dayCol2.setEditable(true);

        TableView<WorkDay> table = new TableView<>();
        table.setEditable(true);
        table.setItems(data);
        table.getColumns().addAll(dayCol, dayCol2);

        stage.setTitle("**Double-click a day to edit it**");
        stage.setScene(new Scene(table));
        stage.show();
    }

    public static class WorkDay {
        private final SimpleObjectProperty<Day> day;

        private WorkDay(Day day) {
            this.day = new SimpleObjectProperty<>(day);
        }

        public Day getDay() {
            return day.get();
        }

        public void setDay(Day day) {
            this.day.set(day);
        }
    }
}