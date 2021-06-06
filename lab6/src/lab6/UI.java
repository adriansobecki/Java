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
import java.awt.FlowLayout;

public class UI {

	private static DBClient db;

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel chartPanelCont;
	private JTextField showCityField;
	private JButton showSubmit;
	private JButton loadSubmit;

	
	public static void main(String[] args) {
		new UI();
	}

	public UI() {
		db = new DBClient();
		initialize();
		addEvents();
	}
	
	/**
	 * Metoda inticjalizuje widok u¿ytkownika
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setVisible(true);
		//frame.setBounds(100, 100, 800, 600);
		frame.setBounds(100, 100, 1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		mainPanel = new JPanel();
		FlowLayout fl_mainPanel = (FlowLayout) mainPanel.getLayout();
		fl_mainPanel.setHgap(0);
		mainPanel.setBounds(210, 10, 765, 33);

		JLabel lblNewLabel = new JLabel("Wy\u015Bwietl histori\u0119 dla miasta: ");
		lblNewLabel.setBounds(10, 434, 147, 14);
		mainPanel.add(lblNewLabel);

		showCityField = new JTextField();
		showCityField.setBounds(184, 426, 111, 20);
		mainPanel.add(showCityField);

		showCityField.setColumns(10);

		showSubmit = new JButton("Pobierz i wy\u015Bwietl");
		showSubmit.setBounds(294, 425, 89, 23);
		mainPanel.add(showSubmit);

		loadSubmit = new JButton("Wczytaj obecn\u0105 historie");
		loadSubmit.setBounds(294, 483, 89, 23);
		mainPanel.add(loadSubmit);
		frame.getContentPane().add(mainPanel);

		chartPanelCont = new JPanel();
		//chartPanelCont.setBounds(10, 53, 764, 497);
		chartPanelCont.setBounds(10, 53, 1164, 497);
		frame.getContentPane().add(chartPanelCont);
		frame.setVisible(true);
	}

	/**
	 * Metoda dodaje eventy do widoku u¿ytkownika. Umo¿liwia to obs³ugê np. klików
	 */
	private void addEvents() {
		showSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String cityName = showCityField.getText();

					Weather newWeather = Api.get(cityName);
					db.addNewWeather(cityName, newWeather);

					plotChartForCity(cityName);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		loadSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String cityName = showCityField.getText();

					plotChartForCity(cityName);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Metoda wyœwietla wykres temperatur na podstawie obecnych danych w bazie
	 * @param cityName - nazwa miasta do wyœwietlenia
	 * @throws SQLException
	 */
	private void plotChartForCity(String cityName) throws SQLException {
		ArrayList<HashMap<String, String>> weathers = db.getWeathersForCity(cityName);

		if (weathers.size() > 0) {
			plotTempChart(weathers, "Historia dla miasta " + cityName);
		}
	}
	
	/**
	 * Metoda umo¿liwia wyœwietlenie wykresu dla podanych danych pogodowych
	 * @param data
	 * @param title
	 */
	private void plotTempChart(ArrayList<HashMap<String, String>> data, String title) {
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
	
	/**
	 * Metoda parsuje dane z bazy do danych obs³ugiwanych przez bibliotekê do wykresów
	 * @param data
	 * @return
	 */
	private DefaultCategoryDataset getTemperatureDatasetFromDBData(ArrayList<HashMap<String, String>> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		data.forEach((weather) -> {
			dataset.setValue(Float.parseFloat(weather.get("temp")), "", weather.get("create_time"));
		});
		
		return dataset;
	}

}

