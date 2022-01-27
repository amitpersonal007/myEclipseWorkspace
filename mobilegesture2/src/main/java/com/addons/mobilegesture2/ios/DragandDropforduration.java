package com.addons.mobilegesture2.ios;

import com.testsigma.sdk.ApplicationType;
import com.testsigma.sdk.IOSAction;
import com.testsigma.sdk.Result;
import com.testsigma.sdk.annotation.Action;
import com.testsigma.sdk.annotation.Element;
import com.testsigma.sdk.annotation.TestData;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
@Action(actionText = "Drag and Hold element1 for test_data seconds and Drop it on element2", applicationType = ApplicationType.IOS)
public class DragandDropforduration extends IOSAction {


    @Element(reference = "element1")
    private com.testsigma.sdk.Element uiIdentifier1;
    @Element(reference = "element2")
    private com.testsigma.sdk.Element uiIdentifier2;
    @TestData(reference = "test_data")
    private com.testsigma.sdk.TestData testdata;

    @Override
    protected Result execute() throws NoSuchElementException {
        //Your Awesome code starts here

        /* @Author - Amit
        What's Inside?
        Drag and Drop from one postion to other where we do click and hold operation for certain duration
         */
        Result result = Result.SUCCESS;

        logger.info("Initiating execution");
        logger.debug("First Ui Identifer: " + this.uiIdentifier1.getValue() + " by:" + this.uiIdentifier1.getBy() +"Second Ui Identifer: "+this.uiIdentifier2.getValue() + "by:"+this.uiIdentifier2.getBy());
        IOSDriver  iosDriver = (IOSDriver) this.driver;
        WebElement element1 = uiIdentifier1.getElement();
        WebElement element2 = uiIdentifier2.getElement();
        if (element1.isDisplayed() && element2.isDisplayed()) {
            logger.info("Element is displayed, entering data");
            setSuccessMessage(String.format("Element Found Proceeding next actions"));
        } else {
            result = Result.FAILED;
            logger.warn("Element with locator %s is not visible ::" + uiIdentifier1.getBy() + "::" + uiIdentifier1.getValue() + uiIdentifier2.getValue()+uiIdentifier2.getBy());
            setErrorMessage(String.format("Element with locator %s is not visible", uiIdentifier1.getBy() +""+ uiIdentifier2.getBy()));
        }
        Point loc1=element1.getLocation();
        Point loc2=element2.getLocation();

        try {
            Thread.sleep(2000);
            TouchAction action =new TouchAction(iosDriver);
            int startX = loc1.getX()+element1.getRect().getWidth()/2;
            int startY = loc1.getY()+element1.getRect().getHeight()/2;

            int endX = loc2.getX()+element2.getRect().getWidth()/2;
            int endY = loc2.getY()+element1.getRect().getHeight();
            Map<String, Object> args = new HashMap<>();
            args.put("duration", Integer.parseInt(testdata.getValue().toString()));
            args.put("fromX", startX);
            args.put("fromY", startY);
            args.put("toX", endX);
            args.put("toY", endY);
            iosDriver.executeScript("mobile: dragFromToForDuration", args);
            action.longPress(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point(endX,endY)).release().perform();
            iosDriver.getScreenshotAs(OutputType.BYTES);
            setSuccessMessage("Drag and drop Success");
        } catch (Exception e) {
            result= Result.FAILED;
            e.printStackTrace();
            setErrorMessage("Error in Drag and Drop"+ e.getCause().toString());


        }

        return result;

    }
}


