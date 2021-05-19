package lab6;

import java.awt.EventQueue;

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


public class UI {

	private JFrame frame;
	private JPanel chartPanelCont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public UI() {
		initialize();
		plotTempChart(getTemperatureDataset(), "Historia pogody");
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		chartPanelCont = new JPanel();
		chartPanelCont.setBounds(10, 10, 765, 400);
		frame.getContentPane().add(chartPanelCont);
	}
	
	private DefaultCategoryDataset getTemperatureDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(10, "", "10");
		dataset.setValue(20, "", "20");
		dataset.setValue(30, "", "30");
		dataset.setValue(40, "", "40");
		return dataset;
	}
	
	private void plotTempChart(DefaultCategoryDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createBarChart(title, "Data i godzina", "Temperatura", dataset , PlotOrientation.VERTICAL, false, false, false);
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
