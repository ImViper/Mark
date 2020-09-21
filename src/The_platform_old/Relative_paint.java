package The_platform_old;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Relative_paint {

	/**参数分别为子句，段落号，存储关系属性的list，存放12种特征的列表*/
	public Relative_paint(ListView<String> duan_text, int para_id, List<List<List<String>>> pian_relative,
			List<String> rela_name) {
		
		String string = "";
		
		Text[] ziju_number = new Text[duan_text.getItems().size()];
		for (int i = 0; i < duan_text.getItems().size(); i++) {
			string += duan_text.getItems().get(i) + "\n";
			ziju_number[i] = new Text((i + 1) + "");
			ziju_number[i].setFont(Font.font(15));
		}
		int[] auto_ziju = new int[ziju_number.length];
		Stage stage = new Stage();
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color:#FFFFFF;");
		Scene scene = new Scene(pane, 1300, 650);

		for (int i = 0; i < ziju_number.length; i++) {
			ziju_number[i].setLayoutX(1300 / ziju_number.length * i + 60);
			ziju_number[i].setLayoutY(600);
			pane.getChildren().add(ziju_number[i]);
		}

		Text ziju_text = new Text("子句");
		ziju_text.setFont(Font.font(15));

		TextArea textArea = new TextArea();
		textArea.setFont(Font.font("NSimSun", 15));
		textArea.setPrefSize(480, 300);
		textArea.setStyle("-fx-background-color:#FFFFFF;");
		textArea.setText(string);
		textArea.setWrapText(true);
		textArea.setEditable(false);
		VBox vBox1 = new VBox();
		vBox1.setLayoutX(10);
		vBox1.setLayoutY(10);
		vBox1.setPadding(new Insets(10));
		vBox1.getChildren().addAll(ziju_text, textArea);
		pane.getChildren().add(vBox1);

		Text rela_text = new Text("关联属性");
		rela_text.setFont(Font.font(15));
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.setPrefSize(777, 300);
		Text guanliandui = new Text("关联对");
		guanliandui.setFont(Font.font(15));
		Text major_text = new Text("主特征");
		major_text.setFont(Font.font(15));
		gridPane.add(guanliandui, 0, 0);
		gridPane.add(major_text, 1, 0);
		for (int i = 0; i < rela_name.size(); i++) {
			Text text = new Text(rela_name.get(i));
			text.setFont(Font.font(15));
			gridPane.add(text, i + 2, 0);
		}
		Color color = new Color(0, 0, 0, 0);
		if (pian_relative.get(para_id).size() > 0) {
			Arc[] arcs = new Arc[pian_relative.get(para_id).size()];
			for (int i = 0; i < pian_relative.get(para_id).size(); i++) {
								
				if (pian_relative.get(para_id).get(i).size() < 6) {
					int j = 0;
					String relation_pair = pian_relative.get(para_id).get(i).get(0);
					Text text1 = new Text(relation_pair);
					text1.setFont(Font.font(15));
					gridPane.add(text1, j, i + 1);
					String[] pairs = relation_pair.split("--");
					int pair1 = Integer.valueOf(pairs[0]) - 1;
					int pair2 = Integer.valueOf(pairs[1]) - 1;
					int widethen = Math.abs(pair2 - pair1);
					double centerX = (ziju_number[pair1].getLayoutX() + ziju_number[pair2].getLayoutX()) / 2.0;
					double radiusX = Math.abs((ziju_number[pair2].getLayoutX() - ziju_number[pair1].getLayoutX())) / 2.0;
					double fix_radiusX = radiusX - 20.0 + (widethen - 1) * 5;
					arcs[i] = new Arc(centerX + 3, ziju_number[0].getLayoutY() - 20, fix_radiusX , radiusX / 2, 0, 180);
					arcs[i].setType(ArcType.OPEN);
					arcs[i].setFill(color);
					arcs[i].setStroke(Color.BLACK);
					pane.getChildren().add(arcs[i]);
					auto_ziju[pair1]++;
					auto_ziju[pair2]++;
					String center = pian_relative.get(para_id).get(i).get(3);
					if (center.equals("1")) {
						Circle circle = new Circle(centerX + 3 - fix_radiusX, ziju_number[pair1].getLayoutY() - 20, 2);
						pane.getChildren().addAll(circle);
					} else {
						if (center.equals("2")) {
							Circle circle = new Circle(centerX + 3 + fix_radiusX, ziju_number[pair1].getLayoutY() - 20, 2);
							pane.getChildren().addAll(circle);
							
						} else {
							if (center.equals("3")) {
								Circle circle1 = new Circle(centerX + 3 - fix_radiusX, ziju_number[pair1].getLayoutY() - 20, 2);
								pane.getChildren().addAll(circle1);
								Circle circle2 = new Circle(centerX + 3 + fix_radiusX, ziju_number[pair1].getLayoutY() - 20, 2);
								pane.getChildren().addAll(circle2);
								
							}
						}
					}
				} else {

					int j = 0;
					String relation_pair = pian_relative.get(para_id).get(i).get(6);
					Text text1 = new Text(relation_pair);
					text1.setFont(Font.font(15));
					gridPane.add(text1, j, i + 1);
					j++;
					Text text2 = new Text(pian_relative.get(para_id).get(i).get(2));
					text2.setFont(Font.font(15));
					gridPane.add(text2, j, i + 1);
					j++;
					String relations = pian_relative.get(para_id).get(i).get(3);
					for (int k = 3; k < relations.length(); k = k + 5) {
						Text text = new Text("   " + relations.charAt(k));
						text.setFont(Font.font(15));
						gridPane.add(text, j, i + 1);
						j++;
					}
					String[] pairs = relation_pair.split("--");
					int pair1 = Integer.valueOf(pairs[0]) - 1;
					int pair2 = Integer.valueOf(pairs[1]) - 1;
					int widethen = Math.abs(pair2 - pair1);
					double centerX = (ziju_number[pair1].getLayoutX() + ziju_number[pair2].getLayoutX()) / 2.0;
					double radiusX = Math.abs((ziju_number[pair2].getLayoutX() - ziju_number[pair1].getLayoutX())) / 2.0;
					String center = pian_relative.get(para_id).get(i).get(11);
					double fix_radiusX = radiusX - 20.0 + (widethen - 1) * 5;
					arcs[i] = new Arc(centerX + 3, ziju_number[0].getLayoutY() - 20, fix_radiusX, radiusX / 2, 0, 180);
					arcs[i].setType(ArcType.OPEN);
					arcs[i].setFill(color);
					arcs[i].setStroke(Color.BLACK);
					pane.getChildren().add(arcs[i]);
					auto_ziju[pair1]++;
					auto_ziju[pair2]++;
					String words = pian_relative.get(para_id).get(i).get(1);
					Text link_words = new Text(centerX - 10, ziju_number[0].getLayoutY() - radiusX / 2 - 20,
							words);
					link_words.setFont(Font.font(15));
					pane.getChildren().add(link_words);
					if (center.equals("1")) {
						Circle circle = new Circle(centerX + 3 - fix_radiusX, ziju_number[pair1].getLayoutY() - 20, 2);
						pane.getChildren().addAll(circle);
					} else {
						if (center.equals("2")) {
							Circle circle = new Circle(centerX + 3 + fix_radiusX, ziju_number[pair1].getLayoutY() - 20, 2);
							pane.getChildren().addAll(circle);
							
						} else {
							if (center.equals("3")) {
								Circle circle1 = new Circle(centerX + 3 - fix_radiusX, ziju_number[pair1].getLayoutY() - 20, 2);
								pane.getChildren().addAll(circle1);
								Circle circle2 = new Circle(centerX + 3 + fix_radiusX, ziju_number[pair1].getLayoutY() - 20, 2);
								pane.getChildren().addAll(circle2);
								
							}
						}
					}
				}
			}
		}

		VBox vBox2 = new VBox();
		vBox2.setLayoutX(500);
		vBox2.setLayoutY(10);
		vBox2.setPadding(new Insets(10));
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(gridPane);
		vBox2.getChildren().addAll(rela_text, scrollPane);
		pane.getChildren().add(vBox2);

		Text pText = new Text(580, 640, "第" + (para_id + 1) + "段");
		pText.setFont(Font.font("NSimSun", 25));

		pane.getChildren().add(pText);

		stage.setScene(scene);
		stage.setTitle("中文关系");
		stage.setResizable(false);

		stage.show();
	}

}
