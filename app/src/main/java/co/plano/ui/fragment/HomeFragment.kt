package co.plano.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import co.plano.R
import co.plano.ui.RoundedBarChartRenderer
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class HomeFragment : Fragment(), OnChartValueSelectedListener {

    lateinit var rlAddChild:RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        rlAddChild=view.findViewById(R.id.rl_add_child)

        rlAddChild.setOnClickListener {
            val btnsheet = layoutInflater.inflate(R.layout.bottom_sheet_child_view, null)
            val dialog = BottomSheetDialog(this.requireContext())
            dialog.setContentView(btnsheet)
            btnsheet.setOnClickListener {
                dialog.dismiss()
            }

            var chart: BarChart? = null

            chart = btnsheet.findViewById(R.id.barChart_day)

            chart!!.description.isEnabled = false
            // if more than 60 entries are displayed in the chart, no values will be
            // drawn

            // if more than 60 entries are displayed in the chart, no values will be
            // drawn
            chart!!.setMaxVisibleValueCount(40)

            // scaling can now only be done on x- and y-axis separately

            // scaling can now only be done on x- and y-axis separately
            chart!!.setPinchZoom(false)

            chart!!.setDrawGridBackground(false)
            chart!!.setDrawBarShadow(false)

            chart!!.setDrawValueAboveBar(false)
            chart!!.isHighlightFullBarEnabled = false

            // change the position of the y-labels

            // change the position of the y-labels
            val leftAxis = chart!!.axisLeft
//            leftAxis.valueFormatter = MyAxisValueFormatter()
            leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

            chart!!.axisRight.isEnabled = false

            val xLabels = chart!!.xAxis
            xLabels.position = XAxisPosition.BOTTOM
            val l = chart.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.orientation = Legend.LegendOrientation.HORIZONTAL
            l.setDrawInside(false)
            l.formSize = 8f
            l.formToTextSpace = 4f
            l.xEntrySpace = 6f
            val values = ArrayList<BarEntry>()
            for (i in 0 until 6) {
                val mul: Float = (i+ 1).toFloat()
                val val1 = (Math.random() * mul).toFloat() + mul / 3
                values.add(
                    BarEntry(
                        i.toFloat(), val1,
                        null
                    )
                )
            }

            val set1: BarDataSet

            if (chart.data != null &&
                chart.data.dataSetCount > 0
            ) {
                set1 = chart.data.getDataSetByIndex(0) as BarDataSet
                set1.values = values
                chart.data.notifyDataChanged()
                chart.notifyDataSetChanged()
            } else {
                set1 = BarDataSet(values, "Statistics Vienna 2014")
                set1.setDrawIcons(false)
                set1.stackLabels = arrayOf("Births")
                val dataSets = ArrayList<IBarDataSet>()
                dataSets.add(set1)
                val data = BarData(dataSets)
//                data.setValueFormatter(MyValueFormatter())
                data.setValueTextColor(Color.WHITE)
                chart.data = data
            }

            val roundedBarChartRenderer =
                RoundedBarChartRenderer(chart, chart.animator, chart.viewPortHandler)
            roundedBarChartRenderer.setmRadius(20f)
            chart.renderer = roundedBarChartRenderer
            chart!!.data?.barWidth = 0.2f
            chart.invalidate()
            dialog.show()
        }



        return view
    }

    override fun onNothingSelected() {
        TODO("Not yet implemented")
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        TODO("Not yet implemented")
    }

}