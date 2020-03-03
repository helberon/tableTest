package table;


import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class KyivTrainsTable {

    private static By gridPath;
    private static By rowPath;
    private static By cellPath;

    private WebElement imageIcon;
    private String trainNumber;
    private String rout;
    private String arrival;
    private String wait;
    private String departure;
    private WebElement schedule;
    private WebElement buyButton;
    private WebElement rowElement;


    public void initPaths() {
        gridPath = By.xpath(".//tbody");
        rowPath = By.xpath(".//tr[not(@style)]");
        cellPath = By.xpath(".//td");
    }

    public KyivTrainsTable() {
        initPaths();
    }

    public KyivTrainsTable(WebElement webElement) {
        this.rowElement = webElement;
        List<WebElement> cells = rowElement.findElements(cellPath);
        this.imageIcon = cells.get(0).findElement(By.xpath(".//img"));
        this.trainNumber = cells.get(1).findElement(By.xpath(".//a")).getText();
        this.rout = cells.get(2).getText();
        this.arrival = cells.get(3).getText();
        this.wait = cells.get(4).getText();
        this.departure = cells.get(5).getText();
        this.schedule = cells.get(6).findElement(By.xpath(".//a"));
        if (cells.get(7).findElements(By.xpath(".//a")).size() > 0) {
            this.buyButton = cells.get(7).findElement(By.xpath(".//a"));
        } else {
            this.buyButton = null;
        }
    }

    public WebElement initTable(WebDriver driver) {
        return driver.findElement(By.xpath("//div[@id='DataTables_Table_0_wrapper']//tbody"));
    }

    @SneakyThrows
    public List<KyivTrainsTable> getAllItems(WebDriver driver) {
        List<KyivTrainsTable> tableList = new ArrayList<>();
        List<WebElement> rows = initTable(driver).findElements(rowPath);
        for (WebElement elem : rows) {
            tableList.add(new KyivTrainsTable(elem));
        }
        return tableList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KyivTrainsTable trainsTable = (KyivTrainsTable) o;
        return Objects.equals(getTrainNumber(), trainsTable.getTrainNumber()) &&
                Objects.equals(getRout(), trainsTable.getRout()) &&
                Objects.equals(getArrival(), trainsTable.getArrival()) &&
                Objects.equals(getWait(), trainsTable.getWait()) &&
                Objects.equals(getDeparture(), trainsTable.getDeparture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrainNumber(), getRout(), getArrival(), getWait(), getDeparture());
    }

    @Override
    public String toString() {
        return "KyivTrains{" +
                "trainNumber='" + trainNumber + '\'' +
                ", rout='" + rout + '\'' +
                ", arrival='" + arrival + '\'' +
                ", wait='" + wait + '\'' +
                ", departure='" + departure + '\'' +
                '}';
    }
}
