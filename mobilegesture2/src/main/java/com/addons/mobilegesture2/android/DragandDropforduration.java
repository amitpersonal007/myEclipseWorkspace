package com.addons.mobilegesture2.android;

import com.testsigma.sdk.AndroidAction;
import com.testsigma.sdk.ApplicationType;
import com.testsigma.sdk.Result;
import com.testsigma.sdk.annotation.Action;
import com.testsigma.sdk.annotation.Element;
import com.testsigma.sdk.annotation.TestData;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
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
@Action(actionText = "Tap and Hold element1 and Drop it on element2", applicationType = ApplicationType.ANDROID)
public class DragandDropforduration extends AndroidAction {


    @Element(reference = "element1")
    private com.testsigma.sdk.Element uiIdentifier1;
    @Element(reference = "element2")
    private com.testsigma.sdk.Element uiIdentifier2;


    @Override
    protected Result execute() throws NoSuchElementException {
        //Your Awesome code starts here

        /* @Author - Amit
        What's Inside?
        Drag and Drop from one postion to other where we do click and hold operation for certain duration
         */
        Result result = Result.SUCCESS;

        logger.info("Initiating execution");
        logger.debug("First Ui Identifer: " + this.uiIdentifier1.getValue() + " by:" + this.uiIdentifier1.getBy() + "Second Ui Identifer: " + this.uiIdentifier2.getValue() + "by:" + this.uiIdentifier2.getBy());
        AndroidDriver androidDriver = (AndroidDriver) this.driver;
        WebElement element1 = uiIdentifier1.getElement();
        WebElement element2 = uiIdentifier2.getElement();
        if (element1.isDisplayed() && element2.isDisplayed()) {
            logger.info("Element is displayed, entering data");
            setSuccessMessage(String.format("Element Found Proceeding next actions"));
        } else {
            result = Result.FAILED;
            logger.warn("Element with locator %s is not visible ::" + uiIdentifier1.getBy() + "::" + uiIdentifier1.getValue() + uiIdentifier2.getValue() + uiIdentifier2.getBy());
            setErrorMessage(String.format("Element with locator %s is not visible", uiIdentifier1.getBy() + "" + uiIdentifier2.getBy()));
        }
logger.info("Starting Operation");
        TouchAction action = new TouchAction(androidDriver);
        action.longPress(new LongPressOptions().withElement(new
                ElementOption().withElement(element1))).perform();
        action.moveTo(new ElementOption().withElement(element2)).release().perform();
        setSuccessMessage("Successfully Dragged and Dropped");

        return result;
    }
}

