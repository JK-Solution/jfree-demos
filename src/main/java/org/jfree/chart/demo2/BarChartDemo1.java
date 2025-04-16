/* ==================
 * BarChartDemo1.java
 * ==================
 *
 * Copyright 2013-2022, by David Gilbert. All rights reserved.
 *
 * https://github.com/jfree/jfree-demos
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   - Neither the name of the JFree organisation nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL OBJECT REFINERY LIMITED BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package org.jfree.chart.demo2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

/**
 * A simple demonstration application showing how to create a bar chart.
 */
public class BarChartDemo1 extends ApplicationFrame {

    private static final long serialVersionUID = 1L;


    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        BarChartDemo1 demo = new BarChartDemo1("图表");
        ChartPanel chartPanel = new ChartPanel(demo.getPieChart());
        chartPanel.setPreferredSize(new Dimension(1000, 600));

        JFrame frame = new JFrame();
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);


        // BarChartDemo1 demo = new BarChartDemo1("图表");
        // demo.pack();
        // UIUtils.centerFrameOnScreen(demo);
        // demo.setVisible(true);
    }

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public BarChartDemo1(String title) {
        super(title);
        // 箱线图chart创建
        // JFreeChart chart = getBoxAndWhiskerChart();

        // 柱状图，折线图，堆叠柱状图chart创建
        // JFreeChart chart = getBasicBarChart();

        // 带误差的柱状图
        // JFreeChart chart = getIntervalChart();

        // 甘特图
        // JFreeChart chart = getGantChart();

        // 散点图
        // JFreeChart chart = getSeriesCollectChart();

        // 时间序列图
        // JFreeChart chart = getTimeSeriesChart();
        
        // 饼图
        JFreeChart chart = getPieChart();
        
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

  
 

    private JFreeChart getPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("C++", 20);
        dataset.setValue("Java", 40);
        dataset.setValue("Python", 30);

        JFreeChart chart = ChartFactory.createPieChart(
            "编程语言占比", dataset, true, true, false
        );
        return chart;
    }

    private JFreeChart getTimeSeriesChart() {
        TimeSeries series = new TimeSeries("温度变化");
        series.add(new Day(1, 4, 2025), 22.5);
        series.add(new Day(2, 4, 2025), 24.8);

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "每日温度", "日期", "℃", dataset
        );
        return chart;
    }

    private JFreeChart getSeriesCollectChart() {
       XYSeries series = new XYSeries("数据点");
        series.add(1, 2);
        series.add(2, 3);
        series.add(3, 4);
        series.add(4, 10);

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createScatterPlot(
            "散点图", "X轴", "Y轴", dataset
        );
        return chart;
    }

    private JFreeChart getGantChart() {
        TaskSeries series = new TaskSeries("项目计划");
        Task task = new Task("开发阶段", 
            new java.util.Date(new java.util.GregorianCalendar(2025, java.util.Calendar.APRIL, 1).getTimeInMillis()), 
            new java.util.Date(new java.util.GregorianCalendar(2025, java.util.Calendar.APRIL, 15).getTimeInMillis())
        );
        series.add(task);

        Task testTask = new Task("测试阶段", 
        new java.util.Date(new java.util.GregorianCalendar(2025, java.util.Calendar.MAY, 1).getTimeInMillis()), 
        new java.util.Date(new java.util.GregorianCalendar(2025, java.util.Calendar.MAY, 15).getTimeInMillis())
    );
        series.add(testTask);
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        dataset.add(series);

        JFreeChart chart = ChartFactory.createGanttChart(
            "项目进度", "任务", "时间", dataset
        );
        return chart;
    }

    private JFreeChart getIntervalChart() {

        String[] rowKeys = {"产品A", "产品B"};
        String[] columnKeys = {"Q1", "Q2"};
        Number[][] startValues = {
            {100, 300},
            {500, 700}
        };
        Number[][] endValues = {
            {200, 400},
            {600, 800}
        };
        DefaultIntervalCategoryDataset dataset = new DefaultIntervalCategoryDataset(rowKeys, columnKeys, startValues, endValues);

        JFreeChart chart = ChartFactory.createBarChart(
            "季度销量", "季度", "销量", dataset,
            PlotOrientation.VERTICAL, true, true, false
        );
        return chart;

    }

    private JFreeChart getBasicBarChart() {
        // 数据准备
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(200, "产品A", "Q1");
        dataset.addValue(300, "产品A", "Q2");

        dataset.addValue(500, "产品B", "Q1");
        dataset.addValue(600, "产品B", "Q2");

        // 生成柱状图图表
        // JFreeChart chart = ChartFactory.createBarChart(
        //     "季度销量", "季度", "销量", dataset
        // );
        
        // 生成折线图
        // JFreeChart chart = ChartFactory.createLineChart("季度销量", "季度", "销量", dataset);

         // 生成堆叠柱状图
         JFreeChart chart = ChartFactory.createStackedBarChart("季度销量", "季度", "销量", dataset);
        return chart;
    }

    /**
     * 箱线图数据组装
     * @return
     */
    private JFreeChart getBoxAndWhiskerChart() {
        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        List<Double> values = Arrays.asList(15.0, 20.0, 25.0, 30.0, 35.0);
        List<Double> values2 = Arrays.asList(1.0, 20.0, 25.0, 30.0, 350.0);
        List<Double> values3 = Arrays.asList(150.0, 200.0, 250.0, 300.0, 350.0);
        dataset.add(values, "类别1", "数据集1");
        dataset.add(values2, "类别2", "数据集2");
        dataset.add(values3, "类别3", "数据集3");

        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(
            "数据分布分析", "类别", "数值", dataset, true
        );
        return chart;
    }

    private static void adjustExcelStyle(JFreeChart chart) {
		// 创建自定义主题
		StandardChartTheme excelTheme = new StandardChartTheme("ExcelStyle");
		// 设置全局字体（解决中文乱码）
		// 标题
		excelTheme.setExtraLargeFont(new Font("微软雅黑", Font.BOLD, 16));
		// 轴标签
		excelTheme.setRegularFont(new Font("微软雅黑", Font.PLAIN, 12));    
		// 图例
		excelTheme.setLargeFont(new Font("微软雅黑", Font.PLAIN, 12));      
		ChartFactory.setChartTheme(excelTheme);
		
		// 修改渲染器颜色
		CategoryPlot plot = chart.getCategoryPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(68, 114, 196)); // Excel蓝色
		renderer.setSeriesPaint(1, new Color(255, 192, 0));  // 橙色
		// 设置背景色
		plot.setBackgroundPaint(Color.WHITE);                // 绘图区白色
		chart.setBackgroundPaint(Color.WHITE);               // 图表整体背景

		// 设置坐标轴
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setAxisLinePaint(Color.BLACK);             // 坐标轴颜色
		rangeAxis.setTickMarkPaint(Color.BLACK);             // 刻度线颜色
		rangeAxis.setMinorTickCount(0);                      // 禁用次要刻度

		// 配置网格线
		plot.setDomainGridlinePaint(new Color(217, 217, 217)); // 浅灰色网格
		plot.setRangeGridlinePaint(new Color(217, 217, 217));
		plot.setDomainGridlineStroke(new BasicStroke(0.75f));  // 线宽0.75pt

		// 调整图例
		LegendTitle legend = chart.getLegend();
		legend.setBorder(0, 0, 0, 0);                        // 去除边框
		legend.setItemFont(new Font("微软雅黑", Font.PLAIN, 10)); // 小号字体

        // CategoryPlot plot = chart.getCategoryPlot();

        // BarRenderer renderer = (BarRenderer) plot.getRenderer();
        // // 禁用渐变绘制
        // renderer.setBarPainter(new GradientBarPainter()); // 使用基础绘制器
        // // 配置系列颜色（需与数据添加顺序一致）
        // renderer.setSeriesPaint(0, new Color(79, 129, 189));  // 线上渠道-蓝色
        // renderer.setSeriesPaint(1, new Color(255, 192, 0));   // 线下门店-橙色

        // // 调整柱形间距（避免堆叠过密）
        // renderer.setItemMargin(0.05);  // 5%间距

        // // 添加数据标签
		// renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		// renderer.setDefaultItemLabelsVisible(true);          // 显示数值
	}

        private void saveChartAsPNG(JFreeChart chart) {
            // 调整图表样式
            adjustExcelStyle(chart);
            // 保存为PNG文件
        try {
            ChartUtils.saveChartAsPNG(
                new File("sales_stacked.png"), 
                chart, 
                1600, 900  // 高分辨率输出
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
