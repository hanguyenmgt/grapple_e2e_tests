package interfaces;

public class PartnerUI {

	public static final String SIGNUP_FORM = "//div[@class='col-xs-12 sinup']";
	public static final String NEXT_STEP_BUTTON = "//button[@type='submit' and text() = 'Next Step']";
	public static final String DYNAMIC_TEXTBOX_WITH_INVALID_CLASS = "//input[@id='%s' and contains(@class ,' invalid ')]";
	public static final String POPUP_MESSAGE = "//div[@ng-bind-html ='vm.message.content | rawHtml']";
	public static final String CLOSE_POPUP_ICON = "//div[@ng-click='vm.dismiss()']";
}
