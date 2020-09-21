package The_platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class MainStage extends Application {



	public static void main(String[] argv) {

		Application.launch(argv);
	}

	int click_ll1_time = 0;

	/** ����ʾ����Ĺ����Խ������� */
	List<List<Integer>> sort;

	String last_read_article = "";
	String now_read_article = "";
	/** ���ڼ�¼����������ʱ�Ƿ����ظ�ѡ��������ᱻԭ�����ı���ɫȥ������ѡ����Ӿ���ɫ */
	int start1 = 0;
	int length1 = 0;
	int start2 = 0;
	int length2 = 0;
	int inbox1_click_times = 0;
	int inbox2_click_times = 0;
	int ibox1_pre_select = -1;
	int ibox2_pre_select = -1;

	/** �ж��Ƿ��и������Ӵʣ�ÿ��ѡȡ���Ӵ�һ�Σ���1 */
	int select_link_words_times = 0;
	/** ���ڸ������Ӵ�ѡȡλ�ã�ÿ��ѡȡ���Ӵ�һ�Σ���1 */
	int count_select_times = 0;

	long time1 = 0, time2 = 0; // �ֱ��¼���������Ժͽ������������������õ�ʱ��
	boolean sub_sentence = false;
	boolean select_position = false;
	Button loading; // ���밴ť
	Button loadingEng;//����Ӣ��
	File save_mkdir; // �����ע�ĵ���Ŀ¼
	File have_read;
	ComboBox<String> filelist; // �����ļ��б�
	RadioButton r1;// ("��ʾ��ϵͼ");
	RadioButton r2;// ("����ʾ��ϵͼ");

	/** С��λ�õĶ�λ */
	List<String> ziju_start;
	List<String> ziju_end;
	/** ���Ӵ�����λ�����䣬�Ա㶨λ���Ӵ�λ�� */
	List<Integer> start_area;
	List<Integer> end_area;

	/** �����Ӿ���ĸ���ť�ֱ�Ϊ���½���ɾ�������棬ȡ�� */
	Button[] four_ziju_bt;
	Button[] relate_bt; // ������ϵ6����ť

	/** �м��ı����� */
	JTextPane center_part;
	JTextPane center_part2;
	SwingNode swingNode;// �����м��ı��Ľڵ�
	String origine_para = "";
	String update_centerpart = "";
	String[] div_para;
	int para_id = 0;
	List<String> pian_text = new ArrayList<>();
	ListView<String> lListView1;
	ListView<String> lListView2;
	ListView<String> ziju = new ListView<>();
	ComboBox<String> inBox1; // �Ӿ�ѡ���
	ComboBox<String> inBox2;

	GridPane gp_in_right1;
	VBox vb_in_right1;
	VBox vb_in_right2;
	ComboBox<String> the_type; // ������ϵ��Ͽ�
	CheckBox can_del; // ��ɾ��ѡ���
	ComboBox<String> can_add; // ��/���������Ͽ�
	ToggleGroup for_yugan; // ��кò�ѡ��
	RadioButton yugan_good;
	RadioButton yugan_bad;
	TextField for_feature; // ���Ӵ�
	String link_words = "";
	Button select_link_words; // ѡȡ���Ӵʰ�ť
	Button del_link_words; // ɾ�����Ӵʰ�ť
	Button add_words_pos; // ������Ӵ�λ�ð�ť
	CheckBox[] relative_and_main; // ������������ѡ���
	CheckBox is_normal; // �Ӿ��Ƿ���ϳ���
	CheckBox center1; // ���ľ�ѡ���
	CheckBox center2;
	Button up_para; // ��һ�ΰ�ť
	Button next_para; // ��һ�ΰ�ť
	String link_words_pos = "";

	/** ʮ���ֹ�ϵ���������б� */
	List<String> the_relative;
	/** һ���еĹ�ϵ���Է����б�������XML�ĵ� */
	List<List<String>> duan_relative;
	/** һƪ����ע�ĵ������й�ϵ���Դ��λ�� */
	List<List<List<String>>> pian_relative;

	/** һƪ���¹�����Ĺ����ԣ����й����ԣ� */
	List<List<String>> relative_sentence;
	/** һƪ���¹������С���ȫ�ֹ����� */
	List<List<String>> center_degree;
	List<String> duan_sentence; // һ����Ĺ�����
	List<String> all_relative; // һ�������������������

	String number; // �����ƪ�º�

	/** �����ж��Ƿ�ֻ��������ԣ������򲻻Ὠ��XML�ĵ� */
	boolean only_save_relation_pair = false;

	boolean creat_relation_pair = false;

	String create_or_fix = "create";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		TextField in_liulan1 = new TextField();
		TextField in_liulan2 = new TextField();
		filelist = new ComboBox<>();
		loading = new Button("����");
		loading.setDisable(true);
		loadingEng = new Button("����Ӣ��");

		have_read = new File("��ȡ��.txt");
		if (have_read.exists()) {
			Scanner input = new Scanner(have_read);
			if (input.hasNext()) {
				filelist.getItems().clear();
				String read_mkdir = input.nextLine();
				File main_dir = new File(read_mkdir);
				if (main_dir.exists()) {
					for (int i = 0; i < main_dir.listFiles().length; i++) {
						filelist.getItems().add(main_dir.listFiles()[i].getName());
					}
					in_liulan1.setText(read_mkdir);
					in_liulan2.setText(input.nextLine());
					last_read_article = input.nextLine();
					filelist.setValue(last_read_article);
					para_id = Integer.valueOf(input.nextLine());
					loading.setDisable(false);

				}
			}
			input.close();
		} else {
			have_read.createNewFile();
		}

		BorderPane mainBorderpane = new BorderPane();


		// �ϲ���
		GridPane In_mtop1 = new GridPane();
		In_mtop1.setPadding(new Insets(10));
		In_mtop1.setHgap(10);
		In_mtop1.setVgap(10);
		Label mainDir = new Label("��Ŀ¼��");
		Button liulan1 = new Button("���");
		in_liulan1.setMaxWidth(200);
		in_liulan1.setEditable(false);
		filelist.setMaxWidth(200);
		liulan1.setOnAction(e -> {
			filelist.getItems().clear();
			DirectoryChooser choose_dir = new DirectoryChooser();
			File dir = choose_dir.showDialog(primaryStage);
			in_liulan1.setText(dir.toString());
			File main_dir = new File(in_liulan1.getText());
			for (int i = 0; i < main_dir.listFiles().length; i++) {
				filelist.getItems().add(main_dir.listFiles()[i].getName());
			}
			filelist.setValue(filelist.getItems().get(0));
		});
		Label savePath = new Label("����·����");
		Button liulan2 = new Button("���");
		in_liulan2.setEditable(false);
		liulan2.setOnAction(e -> {
			DirectoryChooser save = new DirectoryChooser();
			File save_file = save.showDialog(primaryStage);
			in_liulan2.setText(save_file.toString());

			loading.setDisable(false);

		});
		Label fiLabel = new Label("�ļ���");

		loading.setOnAction(e -> {
			Loading_button loading_button = new Loading_button(in_liulan1.getText(), in_liulan2.getText(),
					filelist.getValue());
			loading_button.handle(e);
			save_mkdir = new File(in_liulan2.getText() + "\\" + "�ѱ�ע�ĵ�");
			save_mkdir.mkdir();
			pian_text = loading_button.get_pianduan();
			sort = new ArrayList<>();
			pian_relative = new ArrayList<>();
			relative_sentence = new ArrayList<>();
			center_degree = new ArrayList<>();
			for (int i = 0; i < pian_text.size(); i++) {
				duan_relative = new ArrayList<>();
				duan_sentence = new ArrayList<>();
				List<String> duan_center_degree = new ArrayList<>();
				List<Integer> to_sort = new ArrayList<>();
				sort.add(to_sort);
				pian_relative.add(duan_relative);
				relative_sentence.add(duan_sentence);
				center_degree.add(duan_center_degree);
			}

			now_read_article = filelist.getValue();
			if (!last_read_article.equals(now_read_article)) {
				para_id = 0;
				last_read_article = now_read_article;
			}

			int index1 = filelist.getValue().indexOf(".");
			number = filelist.getValue().substring(0, index1);

			PrintWriter writer;
			try {
				writer = new PrintWriter(have_read);
				writer.println(in_liulan1.getText());
				writer.println(in_liulan2.getText());
				writer.println(filelist.getValue());
				writer.println(para_id + "");
				writer.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			init_loading();

			inbox1_click_times = 0;
			inbox2_click_times = 0;
			ibox1_pre_select = -1;
			ibox2_pre_select = -1;

			if (r1.isSelected()) {
				new Relative_paint(lListView1, para_id, pian_relative, the_relative);
			}

			init_button();

			up_para.setDisable(false);
			next_para.setDisable(false);
		});
		/* Engloading*/
		loadingEng.setOnAction(e ->{
			The_platform.EnglishPane eng = new EnglishPane();
		} );

		VBox for_display = new VBox();
		ToggleGroup toggleGroup = new ToggleGroup();
		r1 = new RadioButton("��ʾ��ϵͼ");
		r2 = new RadioButton("����ʾ��ϵͼ");
		r1.setToggleGroup(toggleGroup);
		r2.setToggleGroup(toggleGroup);
		r2.setSelected(true);
		GridPane.setHgrow(In_mtop1, Priority.ALWAYS);
		filelist.setPrefWidth(300);
		for_display.getChildren().addAll(r1, r2);
		In_mtop1.add(mainDir, 0, 0);
		In_mtop1.add(in_liulan1, 1, 0);
		In_mtop1.add(liulan1, 2, 0);
		In_mtop1.add(savePath, 3, 0);
		In_mtop1.add(in_liulan2, 4, 0);
		In_mtop1.add(liulan2, 5, 0);
		In_mtop1.add(fiLabel, 0, 1);
		In_mtop1.add(filelist, 1, 1);
		In_mtop1.add(loading, 2, 1);
		In_mtop1.add(loadingEng,3,1);
		In_mtop1.add(for_display, 4, 1);
		In_mtop1.setAlignment(Pos.CENTER);

		// ��߲���
		VBox left_part = new VBox();
		left_part.setPadding(new Insets(10));
		Label ziju_info = new Label("С����Ϣ��");
		left_part.getChildren().add(ziju_info);
		lListView1 = new ListView<>();
		lListView1.setPrefSize(250, 150);
		lListView1.setDisable(true);
		left_part.getChildren().add(lListView1);
		String[] four_ziju_operate = { "�½�С��", "ɾ��С��", "����С��", "ȡ��" };
		four_ziju_bt = new Button[4];
		GridPane gp_left_part1 = new GridPane();
		gp_left_part1.setVgap(5);
		gp_left_part1.setHgap(10);
		for (int i = 0; i < four_ziju_bt.length; i++) {
			four_ziju_bt[i] = new Button(four_ziju_operate[i]);
			four_ziju_bt[i].setPrefWidth(125);
			gp_left_part1.add(four_ziju_bt[i], i % 2, i / 2 + 2);
			four_ziju_bt[i].setDisable(true);
		}

		four_ziju_bt[0].setOnAction(e -> {

			boolean flag = true;

			if (!lListView2.getItems().isEmpty()) {
				String warn = "�½���ɾ���־�������֮���й�ϵ�Ĺ����ԣ��Ƿ������";
				Alert alert2 = new Alert(AlertType.WARNING, warn, new ButtonType("ȡ��"),
						new ButtonType("ȷ��", ButtonBar.ButtonData.YES));
				Optional<ButtonType> _buttonType = alert2.showAndWait();
				if (_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
					flag = true;
				} else {
					swingNode.setDisable(true);
					flag = false;
				}
			}
			if (flag) {
				int index = lListView1.getSelectionModel().getSelectedIndex();

				if (index == -1) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("����ѡ���Ӿ䣬���½��Ӿ䣡����");
					alert.showAndWait();
				} else {

					origine_para = pian_text.get(para_id);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							center_part.setText(origine_para);
						}
					});
					new TextColor(0, center_part.getText().length(), java.awt.Color.white, center_part);

					sub_sentence = true;

					init_button();
					swingNode.setDisable(false);
					four_ziju_bt[2].setDisable(false);
					four_ziju_bt[3].setDisable(false);
					relate_bt[0].setDisable(true);
					lListView2.setDisable(true);
					lListView1.setDisable(true);

				}

			}
		});

		lListView1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				four_ziju_bt[0].setDisable(false);
				four_ziju_bt[1].setDisable(false);
				four_ziju_bt[3].setDisable(false);
				relate_bt[0].setDisable(true);
				lListView2.setDisable(true);
				up_para.setDisable(true);
				next_para.setDisable(true);

				new TextColor(0, center_part.getText().length(), java.awt.Color.white, center_part);

				int pos_l1 = lListView1.getSelectionModel().getSelectedIndex();
				if (pos_l1 != -1) {
					new TextColor(start_area.get(pos_l1), lListView1.getItems().get(pos_l1).length(),
							java.awt.Color.yellow, center_part);

				}

			}
		});

		/* ɾ���־䰴ť */
		four_ziju_bt[1].setOnAction(e -> {

			boolean flag = true;
			if (!lListView2.getItems().isEmpty()) {
				String warn = "�½���ɾ���־�������֮���й�ϵ�Ĺ����ԣ��Ƿ������";
				Alert alert2 = new Alert(AlertType.WARNING, warn, new ButtonType("ȡ��"),
						new ButtonType("ȷ��", ButtonBar.ButtonData.YES));
				Optional<ButtonType> _buttonType = alert2.showAndWait();
				if (_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
					flag = true;
				} else {
					flag = false;
				}
			}

			if (flag) {
				int index = lListView1.getSelectionModel().getSelectedIndex();

				if (index == 0) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("����ɾ����һ���Ӿ䣡��!");
					alert.showAndWait();
				} else {

					new TextColor(0, center_part.getText().length(), java.awt.Color.WHITE, center_part);

					String tokens1 = lListView1.getItems().get(index).substring(((index + 1) + "��").length());
					String tokens2 = lListView1.getItems().get(index - 1).substring((index + "��").length());

					for (int i = 0; i < lListView2.getItems().size(); i++) {
						if (lListView2.getItems().get(i).contains(tokens1)
								|| lListView2.getItems().get(i).contains(tokens2)) {

							lListView2.getItems().remove(i);
							pian_relative.get(para_id).remove(i);
							relative_sentence.get(para_id).remove(i);
							i--;
						}
					}
					pian_text.set(para_id, pian_text.get(para_id).replaceAll("/" + tokens1, tokens1));
					lListView1.getItems().clear();
					update_centerpart = "";
					div_para = pian_text.get(para_id).split("/");

					init_start_end();

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							center_part.setText(update_centerpart);
						}
					});
					new TextColor(0, center_part.getText().length(), java.awt.Color.white, center_part);
					if (r1.isSelected()) {
						new Relative_paint(lListView1, para_id, pian_relative, the_relative);
					}

					inbox1_click_times = 0;
					inbox2_click_times = 0;
					ibox1_pre_select = -1;
					ibox2_pre_select = -1;

					init_button();
					int index1 = filelist.getValue().indexOf(".");
					String mark_doc = filelist.getValue().substring(0, index1);

					new Create_txt(save_mkdir, mark_doc, pian_text);

					new Creat_only_rela(save_mkdir, mark_doc, relative_sentence, center_degree);

					new Create_relative_txt(save_mkdir, mark_doc, pian_relative);

					new WriteXML(save_mkdir, mark_doc, pian_relative, pian_text);

				}
			}

		});
		four_ziju_bt[2].setOnAction(e -> {

			int index = lListView1.getSelectionModel().getSelectedIndex();
			String tokens = lListView1.getItems().get(index).substring(((index + 1) + "��").length());
			String mark_num = lListView1.getItems().get(index).split("��")[0];
			for (int i = 0; i < lListView2.getItems().size(); i++) {
				if (lListView2.getItems().get(i).contains(tokens) && lListView2.getItems().get(i).contains(mark_num)) {
					pian_relative.get(para_id).remove(i);
					relative_sentence.get(para_id).remove(i);
					lListView2.getItems().remove(i);
					i--;
				}
			}

			inBox1.getItems().clear();
			inBox2.getItems().clear();
			center_part.setEditable(false);
			lListView1.getItems().clear();
			update_centerpart = "";
			pian_text.set(para_id, center_part.getText());
			div_para = center_part.getText().split("/");

			init_start_end();

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					center_part.setText(update_centerpart);
				}
			});
			if (r1.isSelected()) {
				new Relative_paint(lListView1, para_id, pian_relative, the_relative);
			}
			sub_sentence = false;

			inbox1_click_times = 0;
			inbox2_click_times = 0;
			ibox1_pre_select = -1;
			ibox2_pre_select = -1;

			init_button();
			lListView1.setDisable(false);
			up_para.setDisable(false);
			next_para.setDisable(false);
			int index1 = filelist.getValue().indexOf(".");
			String mark_doc = filelist.getValue().substring(0, index1);

			new Create_txt(save_mkdir, mark_doc, pian_text);

			new Creat_only_rela(save_mkdir, mark_doc, relative_sentence, center_degree);

			new Create_relative_txt(save_mkdir, mark_doc, pian_relative);

			new WriteXML(save_mkdir, mark_doc, pian_relative, pian_text);

		});
		four_ziju_bt[3].setOnAction(e -> {

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					center_part.setText(update_centerpart);
				}
			});
			center_part.setEditable(false);
			new TextColor(0, center_part.getText().length(), java.awt.Color.white, center_part);
			init_button();
			lListView2.setDisable(false);
			up_para.setDisable(false);
			next_para.setDisable(false);
			lListView1.setDisable(false);

		});
		left_part.getChildren().add(gp_left_part1);
		Label relate_info = new Label("��ϵ��Ϣ��");
		relate_info.setPadding(new Insets(10, 0, 5, 0));
		left_part.getChildren().add(relate_info);
		HBox hb_in_left = new HBox(10);
		hb_in_left.setPadding(new Insets(0, 0, 10, 0));

		inBox1 = new ComboBox<>();
		inBox1.setValue("---ѡ���Ӿ�---");
		inBox1.setPrefWidth(125);
		inBox1.setDisable(true);

		inBox1.setOnAction(e -> {

			if (creat_relation_pair) {
				int i = inBox1.getSelectionModel().getSelectedIndex();
				if (i > -1) {

					if (inbox1_click_times > 0) {
						if (ibox1_pre_select != ibox2_pre_select) {
							new TextColor(start_area.get(ibox1_pre_select), length1, java.awt.Color.WHITE, center_part);
						}
					}
					inbox1_click_times++;
					ibox1_pre_select = i;

					length1 = lListView1.getItems().get(i).length();
					new TextColor(start_area.get(i), length1, java.awt.Color.YELLOW, center_part);
				}
			}

		});

		inBox2 = new ComboBox<>();
		inBox2.setPrefWidth(125);
		inBox2.setDisable(true);
		inBox2.setValue("---ѡ��С��---");
		inBox2.setOnAction(e -> {

			if (creat_relation_pair) {
				int j = inBox2.getSelectionModel().getSelectedIndex();
				if (j > -1) {
					if (inbox2_click_times > 0) {
						if (ibox1_pre_select != ibox2_pre_select) {
							new TextColor(start_area.get(ibox2_pre_select), length2, java.awt.Color.WHITE, center_part);
						}
					}
					inbox2_click_times++;
					ibox2_pre_select = j;

					length2 = lListView1.getItems().get(j).length();
					new TextColor(start_area.get(j), length2, java.awt.Color.GREEN, center_part);
				}
			}

		});
		hb_in_left.getChildren().addAll(inBox1, inBox2);
		left_part.getChildren().add(hb_in_left);
		lListView2 = new ListView<>();
		lListView2.setPrefSize(250, 180);
		lListView2.setDisable(true);
		left_part.getChildren().add(lListView2);
		GridPane gp_left_part2 = new GridPane();
		gp_left_part2.setPadding(new Insets(10, 0, 0, 0));
		gp_left_part2.setVgap(5);
		gp_left_part2.setHgap(10);
		String[] value_of_relate_bt = { "�½�������", "�½�/�޸�����", "�½�/�޸�����", "ɾ��������", "���������", "��������", "��������", "ȡ��" };
		relate_bt = new Button[8];
		for (int i = 0; i < relate_bt.length; i++) {
			relate_bt[i] = new Button(value_of_relate_bt[i]);
			relate_bt[i].setPrefWidth(125);
			gp_left_part2.add(relate_bt[i], i / 4, i % 4);
			relate_bt[i].setDisable(true);
		}
		left_part.getChildren().add(gp_left_part2);

		relate_bt[0].setOnAction(e -> {

			inBox1.setValue("---ѡ��С��---");
			inBox2.setValue("---ѡ��С��---");
			new TextColor(0, center_part.getText().length(), java.awt.Color.white, center_part);
			for (int i = 0; i < relative_and_main.length; i++) {
				relative_and_main[i].setSelected(false);
			}
			center1.setSelected(false);
			center2.setSelected(false);
			link_words = "";
			for_feature.setText("");
			only_save_relation_pair = true;
			creat_relation_pair = true;

			init_button();
			lListView1.setDisable(true);
			inBox1.setDisable(false);
			inBox2.setDisable(false);
			lListView2.setDisable(true);

			relate_bt[0].setDisable(true);
			relate_bt[1].setDisable(true);
			relate_bt[4].setDisable(false);
			relate_bt[7].setDisable(false);

			time1 = System.currentTimeMillis();

		});

		relate_bt[4].setOnAction(e -> {
			int i1 = inBox1.getSelectionModel().getSelectedIndex();
			int i2 = inBox2.getSelectionModel().getSelectedIndex();
			System.out.println(i1 + " " + i2);
			boolean flag = false;
			for (int i = 0; i < lListView2.getItems().size(); i++) {
				System.out.println(lListView2.getItems().get(i).split(" ")[1]);
				/*if (lListView2.getItems().get(i).contains((i1 + 1) + "--" + (i2 + 1))) {
					flag = true;
					break;
				}*/
				if (lListView2.getItems().get(i).split(" ")[1].equals((i1 + 1) + "--" + (i2 + 1))) {
					flag = true;
					break;
				}
			}
			if (flag) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("�Ѱ����˹����ԣ�����");
				alert.showAndWait();
			} else {
				save_all();

				create_or_fix = "create";
				if (lListView2.getItems().size() >= lListView1.getItems().size() - 1) {
					relate_bt[1].setDisable(false);
				}
				if (r1.isSelected()) {
					new Relative_paint(lListView1, para_id, pian_relative, the_relative);
				}
				only_save_relation_pair = false;
				creat_relation_pair = false;

				new TextColor(0, center_part.getText().length(), java.awt.Color.WHITE, center_part);

				lListView2.setDisable(false);
				inbox1_click_times = 0;
				inbox2_click_times = 0;
				ibox1_pre_select = -1;
				ibox2_pre_select = -1;
			}

		});

		lListView2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (lListView2.getItems().size() > lListView1.getItems().size() - 1) {
					relate_bt[1].setDisable(false);
				}
				relate_bt[2].setDisable(false);
				relate_bt[3].setDisable(false);
				relate_bt[7].setDisable(false);
				lListView1.setDisable(true);
				relate_bt[0].setDisable(true);
				create_or_fix = "fix";

				new TextColor(0, center_part.getText().length(), java.awt.Color.white, center_part);

				int pos = lListView2.getSelectionModel().getSelectedIndex();
				if (pos != -1) {
					String[] ziju_num = relative_sentence.get(para_id).get(pos).split(" ")[0].split("--");
					inBox1.setValue("С��" + ziju_num[0]);
					inBox2.setValue("С��" + ziju_num[1]);

					int start_pos1 = Integer.valueOf(ziju_num[0]) - 1;
					int start_pos2 = Integer.valueOf(ziju_num[1]) - 1;
					int length1 = lListView1.getItems().get(start_pos1).length();
					int length2 = lListView1.getItems().get(start_pos2).length();
					new TextColor(start_area.get(start_pos1), length1, java.awt.Color.YELLOW, center_part);
					new TextColor(start_area.get(start_pos2), length2, java.awt.Color.GREEN, center_part);
					fix();

					inbox1_click_times = 0;
					inbox2_click_times = 0;
					ibox1_pre_select = -1;
					ibox2_pre_select = -1;
				}

			}
		});

		// �½�/�޸����İ�ť�����¼�
		relate_bt[1].setOnAction(e -> {

			if (create_or_fix.equals("create")) {
				recommand_ceneter();
				init_button();

				next_para.setDisable(false);
				up_para.setDisable(false);
				center1.setDisable(true);
				center2.setDisable(true);
				relate_bt[1].setDisable(true);
				relate_bt[0].setDisable(true);

			} else {

				init_button();
				relate_bt[1].setDisable(true);
				center1.setDisable(false);
				center2.setDisable(false);
				relate_bt[0].setDisable(true);
				relate_bt[5].setDisable(false);
				relate_bt[7].setDisable(false);
			}

		});

		// �������İ�ť�����¼�
		relate_bt[5].setOnAction(e -> {

			int num = 0;
			if (center1.isSelected() && center2.isSelected()) {
				num = 3;
			} else {
				if (center1.isSelected()) {
					num = 1;
				} else {
					if (center2.isSelected()) {
						num = 2;
					}
				}
			}
			if (num == 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("��δѡ�����ľ�");
				alert.showAndWait();
			} else {
				int index = lListView2.getSelectionModel().getSelectedIndex();
				String string = relative_sentence.get(para_id).get(index);

				if (string.endsWith("�޸�")) {
					string = string.substring(0, string.length() - 4);
				} else {
					string = string.substring(0, string.length() - 1);
				}

				string = string + num + " �޸�";

				relative_sentence.get(para_id).set(index, string);

				pian_relative.get(para_id).get(index).set(1, num + "");

				new Create_relative_txt(save_mkdir, number, pian_relative);

				new Creat_only_rela(save_mkdir, number, relative_sentence, center_degree);
				if (r1.isSelected()) {
					new Relative_paint(lListView1, para_id, pian_relative, the_relative);
				}

				init_button();
				relate_bt[1].setDisable(true);
				next_para.setDisable(false);
				up_para.setDisable(false);
			}

		});

		// �½�/�޸����԰�ť�����¼�
		relate_bt[2].setOnAction(e -> {

			init_button();

			relate_bt[1].setDisable(true);
			select_link_words_times = 0;
			count_select_times = 0;
			link_words_pos = "";
			swingNode.setDisable(false);
			lListView2.setDisable(true);
			lListView1.setDisable(true);
			relate_bt[0].setDisable(true);
			relate_bt[6].setDisable(false);
			relate_bt[7].setDisable(false);
			gp_in_right1.setDisable(false);
			vb_in_right1.setDisable(false);
			vb_in_right2.setDisable(false);
			is_normal.setDisable(false);
			center1.setDisable(false);
			center2.setDisable(false);
			time2 = System.currentTimeMillis();
		});

		// ɾ����ϵ��ť�����¼�
		relate_bt[3].setOnAction(e -> {

			int index = lListView2.getSelectionModel().getSelectedIndex();
			if (index < 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("����ѡ��Ҫɾ���Ĺ����ԣ�");
			} else {
				lListView2.getItems().remove(index);

				relative_sentence.get(para_id).remove(index);
				pian_relative.get(para_id).remove(index);
				sort.get(para_id).remove(index);
				sort.get(para_id).remove(index + 1);

				init_button();

				if (r1.isSelected()) {
					new Relative_paint(lListView1, para_id, pian_relative, the_relative);
				}
				new Creat_only_rela(save_mkdir, number, relative_sentence, center_degree);

				new Create_relative_txt(save_mkdir, number, pian_relative);

				new WriteXML(save_mkdir, number, pian_relative, pian_text);

			}

		});

		// �����޸����԰�ť�����¼�
		relate_bt[6].setOnAction(e -> {
			int num = 0;

			for (int i = 0; i < relative_and_main.length; i = i + 2) {
				if (relative_and_main[i].isSelected()) {
					num++;
				}
			}
			if (num > 1 || num == 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("������ֻ��һ��������ǰ�ѹ�ѡ" + num + "��������");
				alert.showAndWait();
			} else {

				if ((!center1.isSelected() && !center2.isSelected())) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("δѡ�����ľ�");
					alert.showAndWait();
				} else {

					save_all();
					relate_bt[1].setDisable(true);
					if (r1.isSelected()) {
						new Relative_paint(lListView1, para_id, pian_relative, the_relative);
					}
					inBox1.setValue("---ѡ��С��---");
					inBox2.setValue("---ѡ��С��---");
					new TextColor(0, center_part.getText().length(), java.awt.Color.white, center_part);

					select_link_words_times = 0;

				}
			}

		});

		// ȡ����ť�����¼�
		relate_bt[7].setOnAction(e -> {
			init_button();

			new TextColor(0, center_part.getText().length(), java.awt.Color.white, center_part);

			select_link_words_times = 0;
			only_save_relation_pair = false;
			creat_relation_pair = false;
			relate_bt[1].setDisable(true);
			lListView1.setDisable(false);
			up_para.setDisable(false);
			next_para.setDisable(false);
			lListView2.setDisable(false);

		});

		// �м䲿��
		swingNode = new SwingNode();
		center_part = new JTextPane();
		swingNode.setContent(center_part);
		java.awt.Font font = new java.awt.Font("����", java.awt.Font.BOLD, 15);
		center_part.setFont(font);
		swingNode.setDisable(true);
		center_part.setEditable(false);
		swingNode.setOnMouseClicked(e -> {

			if (sub_sentence) {
				int pos = center_part.getSelectionEnd();
				String str1;
				String str2;
				String string = center_part.getText();
				if (pos >= string.length()) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("�����ı�������С���з֣�����");
					alert.showAndWait();
				} else {
					if (string.charAt(pos) == '/') {
						str1 = string.substring(0, pos);
						str2 = string.substring(pos + 1);
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								center_part.setText(str1 + str2);
							}
						});

					} else {
						str1 = string.substring(0, pos);
						str2 = string.substring(pos);
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								center_part.setText(str1 + "/" + str2);
							}
						});

					}

				}
			}
			if (select_position && !sub_sentence) {

				if (for_feature.getText().equals("")) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("�����������Ӵʣ�����ѡȡλ��");
					alert.showAndWait();
				} else {

					String[] strings = for_feature.getText().split("\\...");

					if (strings.length == 1) {
						int posi = center_part.getSelectionEnd();

						int rest = 0;
						for (int i = 0; i < start_area.size(); i++) {
							if (start_area.get(i) < posi) {
								rest += ((i + 1) + "��").length();
							} else {
								break;
							}
						}
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText("��ǰѡ��λ��Ϊ" + (posi - rest));
						alert.showAndWait();

						link_words_pos = (posi - rest) + "..." + (posi - rest + for_feature.getText().length() - 1);

					} else {

						if (count_select_times == strings.length) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setContentText("��ǰ��ѡ��" + count_select_times + "���������Ӵʵ�λ�ã���Ҫ�޸Ļ�����ѡ��λ�ã�����ɾ�����Ӵ�");
							alert.showAndWait();
						} else {

							int posi = center_part.getSelectionEnd();

							int rest = 0;
							for (int i = 0; i < start_area.size(); i++) {
								if (start_area.get(i) < posi) {
									rest += ((i + 1) + "��").length();
								} else {
									break;
								}
							}
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setContentText("��" + (count_select_times + 1) + "����ѡ��λ��Ϊ" + (posi - rest));
							alert.showAndWait();

							link_words_pos += (posi - rest) + "..."
									+ (posi - rest + strings[count_select_times].length() - 1);

							count_select_times++;
							if (count_select_times < strings.length) {
								link_words_pos += "--";
							}

						}
					}

				}

			}

		});

		// �ұ߲���
		VBox right_part = new VBox(10);
		right_part.setPadding(new Insets(10));
		gp_in_right1 = new GridPane();
		gp_in_right1.setHgap(10);
		gp_in_right1.setVgap(10);
		Label relate_type = new Label("�������ͣ�");
		relate_type.setAlignment(Pos.CENTER);
		the_type = new ComboBox<>();
		the_type.getItems().add("��ʽ��ϵ");
		the_type.getItems().add("��ʽ��ϵ");
		the_type.setValue("��ʽ��ϵ");
		the_type.setOnAction(e -> {
			if (the_type.getValue().equals("��ʽ��ϵ")) {
				add_words_pos.setDisable(true);
				select_link_words.setDisable(false);
				for_feature.setEditable(false);
				select_position = false;
			} else {
				select_link_words.setDisable(true);
				add_words_pos.setDisable(false);
				for_feature.setEditable(true);
				select_position = true;
			}
		});
		gp_in_right1.add(relate_type, 0, 0);
		gp_in_right1.add(the_type, 1, 0);
		can_del = new CheckBox("��ɾ��");
		can_add = new ComboBox<>();
		can_add.getItems().add("�����");
		can_add.getItems().add("�������");
		can_add.setValue("�������");

		gp_in_right1.add(can_del, 0, 1);
		gp_in_right1.add(can_add, 1, 1);
		yugan_good = new RadioButton("��к�");
		yugan_bad = new RadioButton("��в�");
		yugan_good.setSelected(true);
		for_yugan = new ToggleGroup();
		for_yugan.getToggles().addAll(yugan_good, yugan_bad);
		gp_in_right1.add(yugan_good, 0, 2);
		gp_in_right1.add(yugan_bad, 1, 2);
		Label feature_words = new Label("���Ӵ�:");
		for_feature = new TextField();
		for_feature.setMaxWidth(95);
		gp_in_right1.add(feature_words, 0, 3);
		gp_in_right1.add(for_feature, 1, 3);
		gp_in_right1.setAlignment(Pos.CENTER);
		gp_in_right1.setDisable(true);
		right_part.getChildren().add(gp_in_right1);
		vb_in_right1 = new VBox(10);
		HBox hb_in_vb1 = new HBox(10);
		select_link_words = new Button("ѡȡ���Ӵ�");
		select_link_words.setDisable(true);
		select_link_words.setOnAction(e -> {
			String string = center_part.getSelectedText();
			if (select_link_words_times > 0) {
				string = for_feature.getText() + "..." + string;
				for_feature.setText(string);
				link_words = for_feature.getText();
				int pos1 = center_part.getSelectionStart();
				int pos2 = center_part.getSelectionEnd() - 1;
				int rest = 0;
				for (int i = 0; i < start_area.size(); i++) {
					if (start_area.get(i) < pos1) {
						rest += ((i + 1) + "��").length();
					} else {
						break;
					}
				}
				link_words_pos += "--" + (pos1 - rest) + "..." + (pos2 - rest);
			} else {
				for_feature.setText(string);
				link_words = for_feature.getText();
				int pos1 = center_part.getSelectionStart();
				int pos2 = center_part.getSelectionEnd() - 1;
				int rest = 0;
				for (int i = 0; i < start_area.size(); i++) {
					if (start_area.get(i) < pos1) {
						rest += ((i + 1) + "��").length();
					} else {
						break;
					}
				}

				link_words_pos = (pos1 - rest) + "..." + (pos2 - rest);
			}

			select_link_words_times++;

		});
		del_link_words = new Button("ɾ�����Ӵ�");
		del_link_words.setOnAction(e -> {
			select_link_words_times = 0;
			count_select_times = 0;
			link_words = "";
			for_feature.setText("");
			link_words_pos = "";
		});
		hb_in_vb1.getChildren().addAll(select_link_words, del_link_words);
		hb_in_vb1.setAlignment(Pos.CENTER);
		add_words_pos = new Button("������Ӵ�λ��");
		add_words_pos.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("�����ı�������Ҫ�����Ӵʲ����λ��");
			alert.showAndWait();
			select_position = true;
			swingNode.setDisable(false);
		});

		vb_in_right1.getChildren().addAll(hb_in_vb1, add_words_pos);
		vb_in_right1.setAlignment(Pos.CENTER);
		vb_in_right1.setDisable(true);
		right_part.getChildren().add(vb_in_right1);
		vb_in_right2 = new VBox(5);
		Label relate_feature = new Label("��ϵ������");
		vb_in_right2.getChildren().add(relate_feature);
		File diy_relate = new File("relative.txt");
		Scanner input = new Scanner(diy_relate);
		the_relative = new ArrayList<>();
		while (input.hasNextLine()) {
			the_relative.add(input.nextLine().split(":")[0]);
		}
		relative_and_main = new CheckBox[the_relative.size() * 2];
		HBox hb_in_vb2 = new HBox(10);
		for (int i = 0; i < the_relative.size() * 2; i = i + 2) {
			relative_and_main[i] = new CheckBox();
			relative_and_main[i + 1] = new CheckBox(the_relative.get(i / 2));
			HBox root_hb = new HBox();
			root_hb.getChildren().addAll(relative_and_main[i], relative_and_main[i + 1]);
			hb_in_vb2.getChildren().add(root_hb);
			if ((i + 2) % 3 == 0) {
				vb_in_right2.getChildren().add(hb_in_vb2);
				hb_in_vb2 = new HBox(10);
			}
		}
		vb_in_right2.setDisable(true);
		right_part.getChildren().add(vb_in_right2);
		input.close();
		VBox vb_in_right3 = new VBox(10);
		vb_in_right3.setPadding(new Insets(100, 0, 0, 0));
		is_normal = new CheckBox("С��Խ�ɫ���ϳ���");
		is_normal.setSelected(true);
		is_normal.setDisable(true);
		vb_in_right3.getChildren().add(is_normal);
		GridPane gp_in_vb3 = new GridPane();
		gp_in_vb3.setVgap(10);
		gp_in_vb3.setHgap(10);
		StackPane stackPane1 = new StackPane();
		Rectangle rectangle1 = new Rectangle(88, 30);
		rectangle1.setFill(Color.YELLOW);
		Label ziju1 = new Label("С��1");
		stackPane1.getChildren().addAll(rectangle1, ziju1);
		gp_in_vb3.add(stackPane1, 0, 0);
		center1 = new CheckBox("�����ľ�");
		center1.setDisable(true);
		gp_in_vb3.add(center1, 1, 0);
		StackPane stackPane2 = new StackPane();
		Rectangle rectangle2 = new Rectangle(88, 30);
		rectangle2.setFill(Color.AQUAMARINE);
		Label ziju2 = new Label("С��2");
		stackPane2.getChildren().addAll(rectangle2, ziju2);
		gp_in_vb3.add(stackPane2, 0, 1);
		center2 = new CheckBox("�����ľ�");
		center2.setDisable(true);
		gp_in_vb3.add(center2, 1, 1);
		up_para = new Button("��һ��");
		up_para.setDisable(true);
		up_para.setOnAction(e -> {
			para_id--;

			if (para_id >= 0) {
				update_centerpart = "";
				reading();
				lListView1.getItems().clear();

				origine_para = pian_text.get(para_id);
				div_para = origine_para.split("/");

				init_start_end();
				if (r1.isSelected()) {
					new Relative_paint(lListView1, para_id, pian_relative, the_relative);
				}

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						center_part.setText(update_centerpart);
					}
				});

				new TextColor(0, center_part.getText().length(), java.awt.Color.WHITE, center_part);

				init_button();
				create_or_fix = "create";

				inbox1_click_times = 0;
				inbox2_click_times = 0;
				ibox1_pre_select = -1;
				ibox2_pre_select = -1;

				up_para.setDisable(false);
				next_para.setDisable(false);

				PrintWriter writer;
				try {
					writer = new PrintWriter(have_read);
					writer.println(in_liulan1.getText());
					writer.println(in_liulan2.getText());
					writer.println(filelist.getValue());
					writer.println(para_id + "");
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("��ǰ��Ϊ�׶��䣡��!");
				alert.showAndWait();
				para_id++;
			}
		});
		up_para.setPrefWidth(88);
		next_para = new Button("��һ��");
		next_para.setDisable(true);
		next_para.setOnAction(e -> {
			para_id++;

			if (para_id < pian_text.size()) {
				update_centerpart = "";
				reading();
				lListView1.getItems().clear();

				origine_para = pian_text.get(para_id);
				div_para = origine_para.split("/");

				init_start_end();

				if (r1.isSelected()) {
					new Relative_paint(lListView1, para_id, pian_relative, the_relative);
				}

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						center_part.setText(update_centerpart);
					}
				});

				new TextColor(0, center_part.getText().length(), java.awt.Color.WHITE, center_part);
				init_button();
				create_or_fix = "create";

				inbox1_click_times = 0;
				inbox2_click_times = 0;
				ibox1_pre_select = -1;
				ibox2_pre_select = -1;

				up_para.setDisable(false);
				next_para.setDisable(false);
				PrintWriter writer;
				try {
					writer = new PrintWriter(have_read);
					writer.println(in_liulan1.getText());
					writer.println(in_liulan2.getText());
					writer.println(filelist.getValue());
					writer.println(para_id + "");
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("��ǰ��Ϊβ���䣡��!");
				alert.showAndWait();
				para_id--;
			}
		});
		next_para.setPrefWidth(88);
		gp_in_vb3.add(up_para, 0, 2);
		gp_in_vb3.add(next_para, 1, 2);
		vb_in_right3.getChildren().add(gp_in_vb3);
		vb_in_right3.setAlignment(Pos.CENTER);
		gp_in_vb3.setAlignment(Pos.CENTER);
		right_part.getChildren().add(vb_in_right3);

		mainBorderpane.setTop(In_mtop1);
		mainBorderpane.setLeft(left_part);
		mainBorderpane.setCenter(swingNode);
		mainBorderpane.setRight(right_part);

//		mainBorderpane1.setTop(In_mtop1);
//		mainBorderpane1.setLeft(left_part);
//		mainBorderpane1.setCenter(swingNode);
//		mainBorderpane1.setRight(right_part);

		Scene scene = new Scene(mainBorderpane, 800, 800);
		primaryStage.setTitle("��עƽ̨");
		primaryStage.setScene(scene);
		primaryStage.show();


	}

	public void save_all() {

		int i1 = Integer.valueOf(inBox1.getValue().substring(2)) - 1;
		int i2 = Integer.valueOf(inBox2.getValue().substring(2)) - 1;
		all_relative = new ArrayList<>();

		if (only_save_relation_pair) {
		
			time1 = System.currentTimeMillis() - time1;
			String time_one = String.format("%.2f", time1 / 1000.0);
			String rel1 = "";
			rel1 += (i1 + 1) + "--" + (i2 + 1);
			rel1 += " " + div_para[i1] + "|" + div_para[i2];

			boolean flag = true;
			int j = 0;
			while (j < sort.get(para_id).size()) {
				if (i1 + 1 < sort.get(para_id).get(j)) {
					sort.get(para_id).add(j, i1 + 1);
					sort.get(para_id).add(j + 1, i2 + 1);
					flag = false;
					break;
				} else {
					if (i1 + 1 == sort.get(para_id).get(j)) {
						if (i2 + 1 < sort.get(para_id).get(j + 1)) {
							sort.get(para_id).add(j, i1 + 1);
							sort.get(para_id).add(j + 1, i2 + 1);
							flag = false;
							break;
						} else {
							j = j + 2;
						}
					} else {
						j = j + 2;
					}
				}
			}

			if (flag) {
				sort.get(para_id).add(i1 + 1);
				sort.get(para_id).add(i2 + 1);
			}

			relative_sentence.get(para_id).add(j/2, rel1 + " NO 0");
			lListView2.getItems().add(j / 2, rel1);
			all_relative.add((i1 + 1) + "--" + (i2 + 1));
			all_relative.add("0");
			all_relative.add(time_one);
			all_relative.add(div_para[i1] + "|" + div_para[i2]);
			all_relative.add(ziju_start.get(i1) + "..." + ziju_end.get(i1) + "����" + ziju_start.get(i2) + "..."
					+ ziju_end.get(i2));

			for (int i = 0; i < 8; i++) {
				all_relative.add("����ע");
			}

			pian_relative.get(para_id).add(j / 2, all_relative);

			new Create_relative_txt(save_mkdir, number, pian_relative);
			new WriteXML(save_mkdir, number, pian_relative, pian_text);
			if (center_degree.get(para_id).size() > 0) {
				center_degree.get(para_id).clear();
			}
			new Creat_only_rela(save_mkdir, number, relative_sentence, center_degree);
		} else {
			int pos = lListView2.getSelectionModel().getSelectedIndex();

			time2 = System.currentTimeMillis() - time2;
			String time_two = String.format("%.2f", time2 / 1000.0);
			String time_one;
			String timeString = "";
			time_one = pian_relative.get(para_id).get(pos).get(2);
			if (time_one.length() < 10) {
				timeString = "����������" + time_one + "�룻�������ԣ�" + time_two + "��";
			} else {
				timeString = time_one;
			}

			String rel1 = "";
			rel1 += (i1 + 1) + "--" + (i2 + 1);
			rel1 += " " + div_para[i1] + "|" + div_para[i2] + " YES ";
			all_relative.add((i1 + 1) + "--" + (i2 + 1));
			if (center1.isSelected() && center2.isSelected()) {
				all_relative.add("3");
				rel1 += 3;
			} else {
				if (center1.isSelected()) {
					all_relative.add("1");
					rel1 += 1;
				} else {
					if (center2.isSelected()) {
						all_relative.add("2");
						rel1 += 2;
					} else {
						all_relative.add("0");
						rel1 += 0;
					}

				}
			}

			all_relative.add(timeString);
			all_relative.add(div_para[i1] + "|" + div_para[i2]);
			all_relative.add(ziju_start.get(i1) + "..." + ziju_end.get(i1) + "����" + ziju_start.get(i2) + "..."
					+ ziju_end.get(i2));

			String yugan = "";
			if (yugan_good.isSelected()) {
				yugan = yugan_good.getText();
			} else {
				yugan = yugan_bad.getText();
			}

			link_words = for_feature.getText();
			all_relative.add(the_type.getSelectionModel().getSelectedItem());
			all_relative.add(link_words);

			String feature = "";

			boolean flag = false;
			for (int i = 0; i < relative_and_main.length; i = i + 2) {

				if (relative_and_main[i].isSelected()) {
					all_relative.add(relative_and_main[i + 1].getText());
					flag = true;
					break;
				}
			}
			if (!flag) {
				all_relative.add("");
			}

			for (int i = 1; i < relative_and_main.length; i = i + 2) {

				if (relative_and_main[i].isSelected()) {
					feature += relative_and_main[i].getText() + "+;";
				} else {
					feature += relative_and_main[i].getText() + "-;";
				}

			}

			all_relative.add(feature);
			all_relative.add(link_words_pos);
			all_relative.add(can_add.getSelectionModel().getSelectedItem());

			if (is_normal.isSelected()) {
				all_relative.add("normal");

			} else {
				all_relative.add("abnormal");
			}
			all_relative.add(yugan);

			relative_sentence.get(para_id).set(pos, rel1);
			pian_relative.get(para_id).set(pos, all_relative);
			lListView2.getItems().set(pos,
					"YES " + (i1 + 1) + "--" + (i2 + 1) + " " + div_para[i1] + "|" + div_para[i2]);

			new Create_relative_txt(save_mkdir, number, pian_relative);
			new Creat_only_rela(save_mkdir, number, relative_sentence, center_degree);
			new WriteXML(save_mkdir, number, pian_relative, pian_text);
		}
		init_button();
		next_para.setDisable(false);
		up_para.setDisable(false);
		lListView2.setDisable(false);

	}

	public void fix() {

		for (int i = 0; i < relative_and_main.length; i++) {
			relative_and_main[i].setSelected(false);
		}

		the_type.setValue("��ʽ��ϵ");
		for_feature.setText("");
		center1.setSelected(false);
		center2.setSelected(false);

		duan_relative = pian_relative.get(para_id);
		int pos = lListView2.getSelectionModel().getSelectedIndex();

		int i = 0;
		// �����Բ����ٽ�������չʾ
		i++;
		if (duan_relative.get(pos).get(i).equals("1")) {
			center1.setSelected(true);
			center2.setSelected(false);
		} else {
			if (duan_relative.get(pos).get(i).equals("2")) {
				center1.setSelected(false);
				center2.setSelected(true);
			} else {
				if (duan_relative.get(pos).get(i).equals("3")) {
					center1.setSelected(true);
					center2.setSelected(true);
				} else {
					center1.setSelected(false);
					center2.setSelected(false);
				}
			}
		}
		// ʱ�䲻����ʾ
		i++;
		// �������������
		i++;
		// λ�ò�����ʾ
		i++;

		i++;
		the_type.setValue(duan_relative.get(pos).get(i));
		i++;
		for_feature.setText(duan_relative.get(pos).get(i));
		i++;
		for (int j = 1; j < relative_and_main.length; j = j + 2) {
			if (duan_relative.get(pos).get(i).equals(relative_and_main[j].getText())) {
				relative_and_main[j - 1].setSelected(true);
			} else {
				relative_and_main[j - 1].setSelected(false);
			}
		}
		i++;
		for (int j = 3, k = 1; j < duan_relative.get(pos).get(i).length(); j = j + 5, k = k + 2) {
			if (duan_relative.get(pos).get(i).charAt(j) == '+') {
				relative_and_main[k].setSelected(true);
			} else {
				relative_and_main[k].setSelected(false);
			}
		}
		i++;
		// ���Ӵ�λ�ò����ڽ������뼴�ɵõ�����������
		i++;
		can_add.setValue(duan_relative.get(pos).get(i));
		i++;

		if (duan_relative.get(pos).get(i).equals("normal")) {
			is_normal.setSelected(true);
		} else {
			is_normal.setSelected(false);
		}
		i++;
		if (duan_relative.get(pos).get(i).equals("��к�")) {
			yugan_good.setSelected(true);
		} else {
			yugan_bad.setSelected(true);
		}

	}

	public void init_loading() {

		reading_have();
		reading();

		lListView1.getItems().clear();
		update_centerpart = "";
		origine_para = pian_text.get(para_id);
		div_para = origine_para.split("/");
		init_start_end();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				center_part.setText(update_centerpart);
			}
		});

	}

	public void reading_have() {
		lListView2.getItems().clear();
		int index1 = filelist.getValue().indexOf(".");
		File file2 = new File(save_mkdir + "\\XML�ĵ�\\" + filelist.getValue().substring(0, index1) + ".xml");

		if (file2.exists()) {
			lListView2.setDisable(false);
			new ReadXml(file2, pian_relative, pian_text, sort);

			for (int i = 0; i < pian_relative.size(); i++) {

				duan_sentence = new ArrayList<>();

				for (int i1 = 0; i1 < pian_relative.get(i).size(); i1++) {

					List<String> temp = pian_relative.get(i).get(i1);
					if (temp.get(8).equals("����ע")) {
						duan_sentence.add(temp.get(0) + " " + temp.get(3) + " NO " + temp.get(1));
					} else {
						duan_sentence.add(temp.get(0) + " " + temp.get(3) + " YES " + temp.get(1));
					}
				}

				relative_sentence.set(i, duan_sentence);
			}

			for (int i = 0; i < pian_relative.size(); i++) {

				origine_para = pian_text.get(i);
				div_para = origine_para.split("/");
				init_start_end();

			}

			new Create_relative_txt(save_mkdir, number, pian_relative);

			new Creat_only_rela(save_mkdir, number, relative_sentence, center_degree);

			new Create_txt(save_mkdir, filelist.getValue().substring(0, index1), pian_text);

			// new WriteXML(save_mkdir, number, pian_relative, pian_text);
		}

	}

	public void init_start_end() {

		ziju_start = new ArrayList<>();
		ziju_end = new ArrayList<>();
		start_area = new ArrayList<>();
		end_area = new ArrayList<>();

		int init_length = 0;
		Integer init_area = 0;
		inBox1.getItems().clear();
		inBox2.getItems().clear();
		for (int i = 0; i < div_para.length; i++) {
			lListView1.getItems().add((i + 1) + "��" + div_para[i]);
			inBox1.getItems().add("С��" + (i + 1));
			inBox2.getItems().add("С��" + (i + 1));

			ziju_start.add(init_length + "");
			ziju_end.add(div_para[i].length() + (init_length - 1) + "");

			start_area.add(init_area);
			end_area.add(div_para[i].length() + (i + 1 + "��").length() - 1);

			update_centerpart += (i + 1) + "��" + div_para[i];
			init_length += div_para[i].length();
			init_area += div_para[i].length() + (i + 1 + "��").length();
		}

		for (int i = 0; i < lListView2.getItems().size(); i++) {

			String[] strings;
			strings = pian_relative.get(para_id).get(i).get(3).split("\\|");

			boolean flag1 = false, flag2 = false;
			int new_index1 = 0, new_index2 = 0;
			for (int j = 0; j < div_para.length; j++) {
				if (!flag1 || !flag2) {
					if (div_para[j].equals(strings[0])) {
						new_index1 = (j + 1);
						flag1 = true;
					}
					if (div_para[j].equals(strings[1])) {
						new_index2 = (j + 1);
						flag2 = true;
					}
				} else {
					break;
				}
			}

			pian_relative.get(para_id).get(i).set(0, new_index1 + "--" + new_index2);
			String string = "";
			if (pian_relative.get(para_id).get(i).get(8).equals("����ע")) {
				string = new_index1 + "--" + new_index2 + " " + pian_relative.get(para_id).get(i).get(3) + " NO "
						+ pian_relative.get(para_id).get(i).get(1);
				lListView2.getItems().set(i,
						new_index1 + "--" + new_index2 + " " + pian_relative.get(para_id).get(i).get(3));

			} else {
				string = new_index1 + "--" + new_index2 + " " + pian_relative.get(para_id).get(i).get(3) + " YES "
						+ pian_relative.get(para_id).get(i).get(1);
				lListView2.getItems().set(i,
						"YES " + new_index1 + "--" + new_index2 + " " + pian_relative.get(para_id).get(i).get(3));

			}
			relative_sentence.get(para_id).set(i, string);
		}
	}

	public void reading() {
		
		lListView2.getItems().clear();
		if (relative_sentence.size() > 0) {
			for (int i = 0; i < relative_sentence.get(para_id).size(); i++) {

				String the_setence = relative_sentence.get(para_id).get(i);
				String[] string = the_setence.split(" ");

				/*String[] strings = string[0].split("--");
				boolean flag = true;
				int j = 0;
				while (j < sort.size()) {
					if (Integer.valueOf(strings[0]) < sort.get(j)) {
						sort.add(j, Integer.valueOf(strings[0]));
						sort.add(j + 1, Integer.valueOf(strings[1]));
						flag = false;
						break;
					} else {
						if (Integer.valueOf(strings[0]) == sort.get(j)) {
							if (Integer.valueOf(strings[1]) < sort.get(j + 1)) {
								sort.add(j, Integer.valueOf(strings[0]));
								sort.add(j + 1, Integer.valueOf(strings[1]));
								flag = false;
								break;
							} else {
								j = j + 2;
							}
						} else {
							j = j + 2;
						}
					}
				}

				if (flag) {
					sort.add(Integer.valueOf(strings[0]));
					sort.add(Integer.valueOf(strings[1]));
				}*/

				if (!pian_relative.get(para_id).get(i).get(8).equals("����ע")) {

					lListView2.getItems().add("YES " + string[0] + " " + string[1]);

				} else {

					lListView2.getItems().add(string[0] + " " + string[1]);

				}
			}
		}
	}

	public void init_button() {
		lListView1.setDisable(false);

		inBox1.setDisable(true);
		inBox2.setDisable(true);
		swingNode.setDisable(true);
		for (int i = 0; i < four_ziju_bt.length; i++) {
			four_ziju_bt[i].setDisable(true);
		}
		for (int i = 0; i < relate_bt.length; i++) {
			relate_bt[i].setDisable(true);
		}
		if (inBox1.getItems().size() <= 1) {
			relate_bt[0].setDisable(true);
		} else {
			relate_bt[0].setDisable(false);
		}
		if (lListView1.getItems().size() > 0) {
			lListView1.setDisable(false);
		} else {
			lListView1.setDisable(true);
		}
		if (lListView2.getItems().size() > 0) {
			lListView2.setDisable(false);
		} else {
			lListView2.setDisable(true);
		}

		if (lListView2.getItems().size() >= lListView1.getItems().size() - 1 && lListView2.getItems().size() != 0) {
			relate_bt[1].setDisable(false);
		}

		gp_in_right1.setDisable(true);
		vb_in_right1.setDisable(true);
		vb_in_right2.setDisable(true);
		is_normal.setDisable(true);
		center1.setDisable(true);
		center2.setDisable(true);
		up_para.setDisable(true);
		next_para.setDisable(true);
	}

	int sum_degree = 0;

	public void recommand_ceneter() {

		String[] xianglian = new String[lListView1.getItems().size()];
		boolean[][] r_p = new boolean[lListView1.getItems().size()][lListView1.getItems().size()];

		Arrays.fill(xianglian, "");
		int[] auto = new int[lListView1.getItems().size()];
		for (int i = 0; i < lListView2.getItems().size(); i++) {
			String string = relative_sentence.get(para_id).get(i).split(" ")[0];
			String[] tokens = string.split("--");
			auto[Integer.valueOf(tokens[0]) - 1]++;
			auto[Integer.valueOf(tokens[1]) - 1]++;
			xianglian[Integer.valueOf(tokens[0]) - 1] += tokens[1] + " ";
			xianglian[Integer.valueOf(tokens[1]) - 1] += tokens[0] + " ";
		}
		boolean flag = false;
		for (int i = 0; i < auto.length; i++) {
			if (auto[i] == 0) {
				flag = true;
				break;
			}
		}

		if (flag) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("û�а�ȫ��С���������������");
			alert.showAndWait();
		} else {

			center_degree.get(para_id).clear();

			List<String> degreeList = new ArrayList<>();
			String all_recom = "";
			int num = 0;

			for (int i = 0; i < lListView2.getItems().size(); i++) {

				String string = relative_sentence.get(para_id).get(i).split(" ")[0];
				all_recom += string + "�������Ƽ����ģ�";
				String[] tokens = string.split("--");

				int sum_degree1 = 0;
				int sum_degree2 = 0;

				sum_degree = 0;
				for (int j = 0; j < r_p.length; j++) {
					for (int j2 = 0; j2 < r_p.length; j2++) {
						r_p[j][j2] = false;
					}
				}
				backward(tokens[0], tokens[1], xianglian, r_p);
				sum_degree1 = sum_degree - 1;
				sum_degree = 0;
				for (int j = 0; j < r_p.length; j++) {
					for (int j2 = 0; j2 < r_p.length; j2++) {
						r_p[j][j2] = false;
					}
				}
				backward(tokens[1], tokens[0], xianglian, r_p);
				sum_degree2 = sum_degree - 1;
				degreeList.add("��С���ȫ�֡�ֱ�ӹ����ȷֱ�Ϊ��" + sum_degree2 + " " + sum_degree1 + "��"
						+ auto[Integer.valueOf(tokens[0]) - 1] + " " + auto[Integer.valueOf(tokens[1]) - 1]);
				if (sum_degree1 == sum_degree2) {
					if (auto[Integer.valueOf(tokens[0]) - 1] > auto[Integer.valueOf(tokens[1]) - 1]) {
						num = 1;
						all_recom += "�Ƽ�" + tokens[0] + "Ϊ���ľ�";
					} else {
						if (auto[Integer.valueOf(tokens[0]) - 1] < auto[Integer.valueOf(tokens[1]) - 1]) {
							num = 2;
							all_recom += "�Ƽ�" + tokens[1] + "Ϊ���ľ�";
						} else {
							num = 0;
							all_recom += "���޷��Ƽ�������һ�µ�С��";
						}
					}

				} else {
					if (sum_degree1 > sum_degree2) {
						num = 2;
						all_recom += "�Ƽ�" + tokens[1] + "Ϊ���ľ�";
					} else {
						num = 1;
						all_recom += "�Ƽ�" + tokens[0] + "Ϊ���ľ�";
					}
				}

				all_recom += "\n";

				String string1 = relative_sentence.get(para_id).get(i);
				if (string1.split(" ").length > 4 && string1.endsWith("�޸�")) {
					string1 = string1.substring(0, string1.length() - 4) + num;
				} else {
					string1 = string1.substring(0, string1.length() - 1) + num;
				}

				relative_sentence.get(para_id).set(i, string1);

				pian_relative.get(para_id).get(i).set(1, num + "");

			}

			center_degree.set(para_id, degreeList);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText(all_recom);
			alert.showAndWait();

			new Create_relative_txt(save_mkdir, number, pian_relative);

			new WriteXML(save_mkdir, number, pian_relative, pian_text);

			new Creat_only_rela(save_mkdir, number, relative_sentence, center_degree);
			if (r1.isSelected()) {
				new Relative_paint(lListView1, para_id, pian_relative, the_relative);
			}

		}

	}

	/**
	 * ���øú������Ƽ����ľ䣬ͨ������С���ӹ������������Ƽ����ú����ó����ǵڶ���С��Ĺ�����
	 * 
	 * @param p_pair
	 *            ��֮����ǰ���С��
	 * @param r_pair
	 *            ����ȵ�С��
	 * @param xianglian
	 *            ÿһ��С����������С��
	 * 
	 */
	public void backward(String p_pair, String r_pair, String[] xianglian, boolean[][] r_p) {

		int index = Integer.valueOf(r_pair) - 1;
		r_p[index][Integer.valueOf(p_pair) - 1] = true;
		r_p[Integer.valueOf(p_pair) - 1][index] = true;
		String[] per_xiao_ju = xianglian[index].split(" ");
		if (per_xiao_ju.length <= 1) {
			sum_degree++;
			return;
		} else {
			for (int i = 0; i < per_xiao_ju.length; i++) {
				if (r_p[index][Integer.valueOf(per_xiao_ju[i]) - 1]
						|| r_p[Integer.valueOf(per_xiao_ju[i]) - 1][index]) {
					continue;
				}

				backward(r_pair, per_xiao_ju[i], xianglian, r_p);

			}
		}
		sum_degree++;
	}



}
