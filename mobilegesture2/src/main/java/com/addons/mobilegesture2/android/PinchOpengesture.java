package com.addons.mobilegesture2.android;

import com.testsigma.sdk.AndroidAction;
import com.testsigma.sdk.ApplicationType;
import com.testsigma.sdk.annotation.Action;
import com.testsigma.sdk.annotation.Element;
import com.testsigma.sdk.annotation.TestData;
import io.appium.java_client.android.AndroidDriver;
import lombok.Data;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Action(actionText = "Do Pinch Zoom in action on the element with percent percent(% between 0.00 to 1.00)", applicationType = ApplicationType.ANDROID)
public class PinchOpengesture extends AndroidAction {


    @TestData(reference = "percent")
    private com.testsigma.sdk.TestData testData;
    @Element(reference = "element")
    private com.testsigma.sdk.Element uiIdentifier;


    @Override
    protected com.testsigma.sdk.Result execute() throws NoSuchElementException {
        //Your Awesome code starts here


        com.testsigma.sdk.Result result = com.testsigma.sdk.Result.SUCCESS;

        logger.info("Initiating execution");
        logger.debug("ui-identifier: " + this.uiIdentifier.getValue() + " by:" + this.uiIdentifier.getBy());
        AndroidDriver androidDriver = (AndroidDriver) this.driver;
        WebElement element = uiIdentifier.getElement();
        if (element.isDisplayed()) {
            logger.info("Element is displayed, entering data");
            setSuccessMessage(String.format("Element Found Proceeding next actions"));
        } else {
            result = com.testsigma.sdk.Result.FAILED;
            logger.warn("Element with locator %s is not visible ::" + uiIdentifier.getBy() + "::" + uiIdentifier.getValue());
            setErrorMessage(String.format("Element with locator %s is not visible", uiIdentifier.getBy()));
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("percent", Float.parseFloat(testData.getValue().toString()));
        params.put("left",element.getLocation().getX()+50);
        params.put("top",element.getLocation().getY()+100);
        params.put("width",element.getRect().getHeight()+100);
        params.put("height",element.getRect().getWidth()+100);

        JavascriptExecutor js =androidDriver;
        js.executeScript("mobile: pinchOpenGesture", params);

        setSuccessMessage("Successfully PinchClose gesture performed on element");

        return result;

    }
}

