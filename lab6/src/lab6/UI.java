package lab6;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UI {

	private static DBClient db;

	private JFrame frame;
	private JPanel chartPanelCont;
	private JTextField showCityField;
	private JTextField loadCityField;
	private JButton showSubmit;
	private JButton loadSubmit;

	public UI() {
		db = new DBClient();
		initialize();
		addEvents();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		chartPanelCont = new JPanel();
		chartPanelCont.setBounds(10, 10, 765, 400);
		frame.getContentPane().add(chartPanelCont);

		JLabel lblNewLabel = new JLabel("Wy\u015Bwietl histori\u0119 dla miasta: ");
		lblNewLabel.setBounds(10, 434, 147, 14);
		frame.getContentPane().add(lblNewLabel);

		showCityField = new JTextField();
		showCityField.setBounds(184, 426, 111, 20);
		frame.getContentPane().add(showCityField);
		showCityField.setColumns(10);

		showSubmit = new JButton("Wy\u015Bwietl");
		showSubmit.setBounds(294, 425, 89, 23);
		frame.getContentPane().add(showSubmit);

		JLabel lblWczytajObecnPogod = new JLabel("Wczytaj obecn\u0105 pogod\u0119 dla miasta: ");
		lblWczytajObecnPogod.setBounds(10, 483, 183, 14);
		frame.getContentPane().add(lblWczytajObecnPogod);

		loadCityField = new JTextField();
		loadCityField.setColumns(10);
		loadCityField.setBounds(184, 484, 111, 20);
		frame.getContentPane().add(loadCityField);

		loadSubmit = new JButton("Wczytaj");
		loadSubmit.setBounds(294, 483, 89, 23);
		frame.getContentPane().add(loadSubmit);
	}

	private void addEvents() {
		showSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<HashMap<String, String>> weathers;
				try {

					String cityName = showCityField.getText();
					weathers = db.getWeathersForCity(cityName);

					if (weathers.size() > 0) {
						plotTempChart(weathers, "Historia dla miasta " + cityName);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		loadSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<HashMap<String, String>> weathers;
				try {

					String cityName = loadCityField.getText();

					//#############################
					// Tu pobieramy dane z api
					//#############################
					
					db.addNewWeather(cityName, 20);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public DefaultCategoryDataset getTemperatureDatasetFromDBData(ArrayList<HashMap<String, String>> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		data.forEach((weather) -> {
			dataset.setValue(Float.parseFloat(weather.get("temp")), "", weather.get("create_time"));
		});

		return dataset;
	}

	public void plotTempChart(ArrayList<HashMap<String, String>> data, String title) {
		DefaultCategoryDataset dataset = getTemperatureDatasetFromDBData(data);

		JFreeChart chart = ChartFactory.createBarChart(title, "Data i godzina", "Temperatura", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		CategoryPlot catPlot = chart.getCategoryPlot();
		catPlot.setRangeGridlinePaint(Color.black);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanelCont.removeAll();
		chartPanelCont.setLayout(new BorderLayout(0, 0));
		chartPanelCont.add(chartPanel);
		chartPanel.setLayout(new BorderLayout(0, 0));
		chartPanelCont.validate();
	}
}
