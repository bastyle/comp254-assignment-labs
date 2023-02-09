package ca.centennial.comp254.lab2.exercise2.bastian.bastias;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphicalRepresentation extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GraphicalRepresentation(final String title) {

		super(title);
	}

	public void printGraph(XYSeries s1, XYSeries s2) {
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.setIntervalWidth(0);
		dataset.addSeries(s1);
		dataset.addSeries(s2);
		
		final JFreeChart chart = ChartFactory.createXYLineChart("Prefix Average Graph", // chart title
				"Category", // domain axis label
				"Value", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, false);

		final XYPlot plot = chart.getXYPlot();
		final NumberAxis domainAxis = new NumberAxis("Records processes");
		final NumberAxis rangeAxis = new LogarithmicAxis("Time (milliseconds)");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		//chart.setBackgroundPaint(Color.white);
		//plot.setOutlinePaint(Color.black);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(720, 600));
		setContentPane(chartPanel);

	}

	public static void main(final String[] args) {

		final GraphicalRepresentation demo = new GraphicalRepresentation("Prefix Average Graph");
//		demo.printGraph();
		demo.pack();
		// RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}
}
