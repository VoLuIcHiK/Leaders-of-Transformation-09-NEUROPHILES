package com.example.hellofigma.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import com.example.hellofigma.camoletappbar.CamoletAppBar
import com.example.hellofigma.data.models.MonitoringBuildingGroup
import com.example.hellofigma.data.models.MonitoringBuildingItem
import com.example.hellofigma.makevideo.MakeVideo
import com.example.hellofigma.monitoringitembuildingnew.MonitoringItemBuildingNew
import com.example.hellofigma.monitoringitembuildingnew.Open
import com.example.hellofigma.monitoringitembuildingsubitem.MonitoringItemBuildingSubItem
import com.example.hellofigma.monthmonitoringlabel.MonthLabel
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import java.nio.channels.AsynchronousServerSocketChannel
import java.util.Calendar


val startingDate: Calendar = Calendar.Builder().setDate(2023, 5, 22).build()

object MonitoringBuildingGroupProvider {

    var monitoringItems: List<MonitoringBuildingGroup>

    init {
        monitoringItems = listOf(
            MonitoringBuildingGroup(name="ЖК Жульен", date=startingDate.time, coordinates = "55.499117, 37.517850", opened = true),
            MonitoringBuildingGroup(name="ЖК Жульен", date=Calendar.Builder().setDate(2023, 5, 23).build().time, coordinates = "55.499117, 37.517850"),
            MonitoringBuildingGroup(name="ЖК Жульен", date=Calendar.Builder().setDate(2023, 6, 2).build().time, coordinates = "55.499117, 37.517850"),
        )
        monitoringItems[0].items = listOf(
            MonitoringBuildingItem(name="Обход № 123. Корпус №1.1", date=monitoringItems[0].date, coordinates = "55.499117, 37.517850")
        )
    }

}

@Composable
@ReadOnlyComposable
fun getLocale(): java.util.Locale {
    val configuration = LocalConfiguration.current
    return ConfigurationCompat.getLocales(configuration).get(0) ?: LocaleListCompat.getDefault()[0]!!
}

fun getRussianMonthName(monthNum: Int): String {
    val monthsNames = listOf(
        "Январь","Февраль","Март","Апрель",
        "Май","Июнь","Июль","Август",
        "Сентябрь", "Октябрь","Ноябрь","Декабрь"
    )
    if (monthNum < 1) throw IllegalArgumentException("Less than 1")
    if (monthNum > 12) throw IllegalArgumentException("More than 12")
    return monthsNames[monthNum+1]
}

var a = 1
@Composable
fun MonitoringScreen() {
    // var monitoringItems = listOf<MonitoringBuildingGroup>(MonitoringBuildingGroupProvider.monitoringItems[0])

    Scaffold(
        topBar = {
            CamoletAppBar(Modifier.fillMaxWidth())
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp, top = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MakeVideo()
            }
        }
    ) { scaffoldPadding ->
        Surface(
            Modifier.padding(scaffoldPadding),
        ) {
            Main()
        }
    }
}

@Preview
@Composable
fun Main() {
    val monitoringItems by remember {mutableStateOf(MonitoringBuildingGroupProvider.monitoringItems)}
    Surface(Modifier.fillMaxWidth()) {


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .fillMaxWidth()
        ) {

            var lastMonth = 0
            items(monitoringItems.size) { i ->
                val monitoringItem = monitoringItems[i]
                val dateCalendar = Calendar.getInstance()
                dateCalendar.time = monitoringItem.date
                val monthNum = dateCalendar.get(Calendar.MONTH)
                val monthName = getRussianMonthName(monthNum)
                val showMonth = lastMonth != monthNum
                lastMonth = monthNum
                val hasItems = monitoringItem.items.isNotEmpty()
                val itemItems = monitoringItem.items
                Column(horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxWidth(0.87f)
                ) {
                    if (showMonth) {
                        MonthLabel(monthName = monthName)
                    }
                    MonitoringBuildingGroupByModel(monitoringItem)
                    if (hasItems) {
                        // НЕльзя использовать вложенные списки
                        FlowColumn(
                            Modifier
                                .padding(top = 8.dp),
                            mainAxisSpacing = 8.dp,
                            crossAxisAlignment = FlowCrossAxisAlignment.End,
                            mainAxisAlignment = FlowMainAxisAlignment.End,
                        ) {
                            itemItems.forEach { itemInItem ->
                                MonitoringBuildingSubItemByModel(
                                    itemInItem,
                                )
                            }
                        }
                    }
                }
            }

        }


    }
}

@Composable
fun MonitoringBuildingGroupByModel(group: MonitoringBuildingGroup, modifier: Modifier = Modifier) {
    val dateCalendar = Calendar.getInstance()
    dateCalendar.time = group.date
    val dayNum = dateCalendar.get(Calendar.DAY_OF_MONTH)
    val monthNum = dateCalendar.get(Calendar.MONTH)
    val monthName = getRussianMonthName(monthNum)
    val yearNum = dateCalendar.get(Calendar.YEAR)
    dateCalendar.time = group.date
    var open = Open.NotExist
    if (group.items.isNotEmpty()) {
        open = if (group.opened) {
            Open.Yes
        } else {
            Open.No
        }
    }
    MonitoringItemBuildingNew(
        open = open,
        dateNumber = String.format("%02d", dayNum),
        dateFull = "$monthName $dayNum $yearNum",
        projectName = group.name,
        coordinates = group.coordinates,
        modifier = modifier
    )
}

@Preview
@Composable
fun MonitoringBuildingGroupPreview() {
    MonitoringBuildingGroupByModel(MonitoringBuildingGroupProvider.monitoringItems[2])
}

@Composable
fun MonitoringBuildingSubItemByModel(item: MonitoringBuildingItem, modifier: Modifier = Modifier) {
    val dateCalendar = Calendar.getInstance()
    dateCalendar.time = item.date
    val dayNum = dateCalendar.get(Calendar.DAY_OF_MONTH)
    val monthNum = dateCalendar.get(Calendar.MONTH)
    val monthName = getRussianMonthName(monthNum)
    val yearNum = dateCalendar.get(Calendar.YEAR)
    MonitoringItemBuildingSubItem(modifier,
        projectName = item.name,
        coordinates = item.coordinates,
        fullDate = "$monthName $dayNum $yearNum"
    )
}

@Preview
@Composable
fun MonitoringBuildingSubItemByModelPreview() {
    MonitoringBuildingSubItemByModel(
        item = MonitoringBuildingGroupProvider.monitoringItems[0].items[0]
    )
}
