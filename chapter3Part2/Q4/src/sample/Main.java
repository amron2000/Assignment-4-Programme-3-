package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class Main extends Application {
    Stage window2;
    ListView<Student> listView;
    List<Student> list;
    Student student;
    Button add;
    TextField idField;
    TextField nameField;
    TextField majorField;
    TextField gradeField;

    public void start(Stage window) {
        window2 = new Stage();
        Button add = new Button("Add Student");
        add.setId("Buttons");
        Button view = new Button("View Student");
        view.setId("Buttons");
        VBox vbox = new VBox(10, add, view);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 300, 300);
        scene.getStylesheets().add("Css.css");
        vbox.setId("BackGround");
        window.setTitle("Options Page");
        window.setScene(scene);
        window.show();
/////////////////// Scene 2 //////////////////////
        EventHandler1 eventHandler = new EventHandler1();
        add.setOnAction(eventHandler);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class EventHandler1 implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(25, 20, 10, 30));
            grid.setHgap(0);
            grid.setVgap(5);
            Label student = new Label("Student Data");
            student.setId("Welcome");
            grid.add(student, 0, 0);
            // id label
            Label Id = new Label("Id:");
            grid.add(Id, 0, 1);
            idField = new TextField();
            grid.add(idField, 1, 1);
            //
            Label name = new Label("Name:");
            grid.add(name, 0, 2);
            nameField = new TextField();
            grid.add(nameField, 1, 2);
            //
            Label major = new Label("Major:");
            grid.add(major, 0, 3);
            majorField = new TextField();
            grid.add(majorField, 1, 3);
            //
            Label grade = new Label("Grade:");
            grid.add(grade, 0, 4);
            gradeField = new TextField();
            grid.add(gradeField, 1, 4);
            //
            add = new Button("Add");
            add.setId("Buttons");
            Button reset = new Button("Reset");
            reset.setId("Buttons");
            Button exit = new Button("Exit");
            exit.setId("Buttons");


            listView = new ListView<>();
            listView.setPrefSize(400, 250);
            grid.add(listView, 2, 1, 7, 7);

            Button nameSort = new Button("NSort");
            nameSort.setOnAction(nameSo -> listView.getItems().setAll(listView.getItems().stream()
                    .sorted(Comparator.comparing(Student::getName))
                    .collect(toList())));

            Button gradeSort = new Button("GSort");
            gradeSort.setOnAction(gradeSo -> listView.getItems().setAll((Student) listView.getItems().stream()
                    .sorted(Comparator.comparing(Student::getGrade))
                    .map(e -> e.getName() + "       " + e.getGrade())
                    .collect(toList())));

            Button gradeSort2 = new Button("GSort2");
            gradeSort2.setOnAction(gradeSo2 -> listView.getItems().setAll(listView.getItems().stream()
                    .filter(e -> e.getGrade() >= 80 && e.getGrade() <= 90)
                    .map(e -> new Student(e.getName(), e.getGrade()))
                    .sorted(Comparator.comparingDouble(Student::getGrade)
                            .reversed())
                    .collect(toList())));

            TextField setText = new TextField();

            Button Average = new Button("AveSort");
            Average.setOnAction( actionEvent1 -> {
                        double Avg =
                                list.stream()
                                        .mapToDouble((Student s) -> s.getGrade())
                                        .average().getAsDouble();
                        String average = null;
                        //  average.value("Average:" + Avg)});
                    });


            Button Major = new Button("MjSort");
            Major.setOnAction(Mj -> listView.getItems().setAll(listView.getItems().stream()
                    .sorted(Comparator.comparing(Student::getMajor))
                    .collect(toList())));


            HBox hbox = new HBox(10, add, reset, exit);
            grid.add(hbox, 1, 5);

            HBox hbox2 = new HBox(8,nameSort, gradeSort, gradeSort2,Average,Major);
            grid.add(hbox2,1,6);

            EventHandler2 eventHandler = new EventHandler2();
            add.setOnAction(eventHandler);


            reset.setOnAction(event -> {
                idField.setText("");
                nameField.setText("");
                majorField.setText("");
                gradeField.setText("");
            });

            exit.setOnAction(event -> window2.close());

            Scene scene = new Scene(grid, 700, 570);
            scene.getStylesheets().add("Css.css");
            window2.setTitle("Options Page");
            window2.setScene(scene);
            window2.setResizable(false);
            window2.show();
        }
    }

    private class EventHandler2 implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == add) {
                if (!idField.getText().equals("") && nameField.getText() != null && majorField.getText() != null && gradeField.getText() != null) {
                    int studentId = Integer.parseInt(idField.getText());
                    String studentName = nameField.getText();
                    String studentMajor = majorField.getText();
                    double studentGrade = Double.parseDouble(gradeField.getText());
                    student = new Student(studentId, studentName, studentMajor, studentGrade);
                    list = new ArrayList<>(Collections.singletonList(student));
                    listView.getItems().addAll(list);
                }
            }

        }

    }

}



