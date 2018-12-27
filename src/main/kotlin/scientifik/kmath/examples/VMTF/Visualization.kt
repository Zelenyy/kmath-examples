package scientifik.kmath.examples.VMTF

import hep.dataforge.meta.configure
import hep.dataforge.meta.createStyle
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import scientifik.kmath.operations.DoubleField
import scientifik.kmath.structures.NDElement
import scientifik.kplot.appendXY
import scientifik.kplot.jfreechart.JFreeChartFrame
import scientifik.kplot.specifications.XYPlot
import scientifik.kplot.specifications.XYPlotSpec
import scientifik.kplot.xyPlot
import tornadofx.*


class JFreeChartFrameTest : App(MasterView::class)

fun main(args: Array<String>) {
    Application.launch(JFreeChartFrameTest::class.java)


}

class MasterView : View() {
    val topView = find(ChartView::class)
    val bottomView = find(ButtonView::class)

    override val root = borderpane {
        top = topView.root
        bottom = bottomView.root
    }
}


class ChartView : View() {
    private val frame = JFreeChartFrame()
    override val root = borderpane {
        center = frame.root
    }


    private val lineStyle = XYPlot.createStyle {
        connectionType = XYPlotSpec.ConnectionType.STEP
        thickness = 4
        showErrors = false
    }

    fun update(x: NDElement<Double, DoubleField>, y: NDElement<Double, DoubleField>) {

        frame["test"] = xyPlot(style = lineStyle) {
            (x.toList() zip y.toList()).forEach { appendXY(it.first, it.second) }
        }.configure(XYPlot) {
            title = "My test plot"
            //showLines = false
        }
    }
}


class ButtonView : View() {
    override val root = HBox()
    private val chartView = find(ChartView::class)

    init {
        with(root) {
            this += Button("Task 1").apply {
                action {
//                    val exact = T_VALUES.map { it.second to exactSolution(it.second) }.forEach {
//                        chartView.update(X_VALUES, it.second)
//                        Thread.sleep(100)
//                    }

                    chartView.update(T_VALUES, T_VALUES)

                }
            }
        }
    }


}