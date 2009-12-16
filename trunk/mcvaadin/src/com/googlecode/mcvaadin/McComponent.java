package com.googlecode.mcvaadin;

import java.util.Collection;

import com.googlecode.mcvaadin.helpers.UIBuilder;
import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.Action.Container;
import com.vaadin.event.Action.Handler;
import com.vaadin.terminal.ParameterHandler;
import com.vaadin.terminal.URIHandler;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Select;
import com.vaadin.ui.Slider;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UriFragmentUtility;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.terminal.Resource;

/**
 * A generic composite component.
 *
 *This class can be used a super class for any UI composites. It offers the
 * {@link UIBuilder} functions to allow simple creation of user interfaces for a
 * Vaadin application.
 *
 * Inherit this class and implement the abstract {@link #ui()} function to build
 * the user interface.
 *
 * @see McApplication
 *@see McWindow
 *
 */
public abstract class McComponent extends CustomComponent {

    /** Generated serial version UID. */
    private static final long serialVersionUID = -6561778192638231101L;
    private VerticalLayout root;
    private UIBuilder uiBuilder;

    public McComponent() {
        this(true);
    }
    public McComponent(boolean initUI) {
        root = new VerticalLayout();
        root.setSpacing(true);
        setCompositionRoot(root);
        uiBuilder = new UIBuilder(root);
        if (initUI) {
            ui();
        }
    }

    /**
     * Function that builds up the content. This function is invoked from the
     * default constructor. By default does nothing and should be overridden to
     * build content.
     */
    public abstract void ui();

    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    // ---------------------- ONLY GENERATED CONTENT BELOW THIS
    // ------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------
    /* GENERATED DELEGATES START */
public String tr(String string0) { return uiBuilder.tr(string0); }
public String tr(String string0, Object... object1) { return uiBuilder.tr(string0, object1); }
public Table table() { return uiBuilder.table(); }
public void error(String string0, String string1) {  uiBuilder.error(string0, string1); }
public void error(String string0, String string1, Throwable throwable2) {  uiBuilder.error(string0, string1, throwable2); }
public void error(Throwable throwable0) {  uiBuilder.error(throwable0); }
public void error(String string0, Throwable throwable1) {  uiBuilder.error(string0, throwable1); }
public void error(String string0) {  uiBuilder.error(string0); }
public void warning(String string0, String string1) {  uiBuilder.warning(string0, string1); }
public void warning(String string0) {  uiBuilder.warning(string0); }
public void expand(Component component0, float float1) {  uiBuilder.expand(component0, float1); }
public Label text(String string0) { return uiBuilder.text(string0); }
public Window window() { return uiBuilder.window(); }
public Window window(String string0, int int1, int int2) { return uiBuilder.window(string0, int1, int2); }
public Label label() { return uiBuilder.label(); }
public Label label(String string0) { return uiBuilder.label(string0); }
public void align(Component component0, Alignment alignment1) {  uiBuilder.align(component0, alignment1); }
public UIBuilder build(ComponentContainer componentcontainer0) { return uiBuilder.build(componentcontainer0); }
public McApplication getApp() { return uiBuilder.getApp(); }
public Window getMainWin() { return uiBuilder.getMainWin(); }
public Embedded browser(String string0) { return uiBuilder.browser(string0); }
public Embedded img(Resource resource0, int int1, int int2) { return uiBuilder.img(resource0, int1, int2); }
public Embedded img(String string0) { return uiBuilder.img(string0); }
public Embedded img(Resource resource0) { return uiBuilder.img(resource0); }
public Embedded img(String string0, int int1, int int2) { return uiBuilder.img(string0, int1, int2); }
public Embedded embedded(Resource resource0, int int1, int int2, int int3) { return uiBuilder.embedded(resource0, int1, int2, int3); }
public Label h1(String string0) { return uiBuilder.h1(string0); }
public Label h2(String string0) { return uiBuilder.h2(string0); }
public Label hr() { return uiBuilder.hr(); }
public Label pre(String string0) { return uiBuilder.pre(string0); }
public Label html(String string0) { return uiBuilder.html(string0); }
public TextField textfield(String string0, String string1, int int2) { return uiBuilder.textfield(string0, string1, int2); }
public TextField textfield(String string0, String string1) { return uiBuilder.textfield(string0, string1); }
public TextField textfield(String string0, String string1, int int2, int int3) { return uiBuilder.textfield(string0, string1, int2, int3); }
public GridLayout gridlayout(int int0, int int1) { return uiBuilder.gridlayout(int0, int1); }
public Button confirmbutton(String string0, String string1, McListener mclistener2) { return uiBuilder.confirmbutton(string0, string1, mclistener2); }
public Button confirmbutton(String string0, String string1, String string2, String string3, McListener mclistener4) { return uiBuilder.confirmbutton(string0, string1, string2, string3, mclistener4); }
public void addUriHandler(URIHandler urihandler0) {  uiBuilder.addUriHandler(urihandler0); }
public void addParamHandler(ParameterHandler parameterhandler0) {  uiBuilder.addParamHandler(parameterhandler0); }
public Link link(String string0, String string1, String string2) { return uiBuilder.link(string0, string1, string2); }
public Panel panel() { return uiBuilder.panel(); }
public HorizontalLayout horizontallayout() { return uiBuilder.horizontallayout(); }
public VerticalLayout verticallayout() { return uiBuilder.verticallayout(); }
public HorizontalLayout horizontal() { return uiBuilder.horizontal(); }
public VerticalLayout vertical() { return uiBuilder.vertical(); }
public Handler bindKey(Container container0, int int1, int[] int2, McListener mclistener3) { return uiBuilder.bindKey(container0, int1, int2, mclistener3); }
public Button bindKey(Button button0, int int1, int[] int2) { return uiBuilder.bindKey(button0, int1, int2); }
public Button bindKey(Button button0, int int1, int int2) { return uiBuilder.bindKey(button0, int1, int2); }
public Button bindKey(Button button0, int int1) { return uiBuilder.bindKey(button0, int1); }
public Table bindData(Table table0, Collection collection1) { return uiBuilder.bindData(table0, collection1); }
public AbstractSelect bindData(AbstractSelect abstractselect0, Collection collection1, String string2) { return uiBuilder.bindData(abstractselect0, collection1, string2); }
public Form bindData(Form form0, Object object1, String[] string2) { return uiBuilder.bindData(form0, object1, string2); }
public String getResourceBundleName() { return uiBuilder.getResourceBundleName(); }
public void setResourceBundleName(String string0) {  uiBuilder.setResourceBundleName(string0); }
public void alert(String string0) {  uiBuilder.alert(string0); }
public void alert(String string0, String string1) {  uiBuilder.alert(string0, string1); }
public Window confirm(String string0, String string1, McListener mclistener2) { return uiBuilder.confirm(string0, string1, mclistener2); }
public Window confirm(String string0, String string1, String string2, String string3, McListener mclistener4) { return uiBuilder.confirm(string0, string1, string2, string3, mclistener4); }
public Window confirm(String string0, McListener mclistener1) { return uiBuilder.confirm(string0, mclistener1); }
public void notification(String string0) {  uiBuilder.notification(string0); }
public void notification(String string0, String string1) {  uiBuilder.notification(string0, string1); }
public void trayNotification(String string0) {  uiBuilder.trayNotification(string0); }
public void trayNotification(String string0, String string1) {  uiBuilder.trayNotification(string0, string1); }
public void add(Component component0) {  uiBuilder.add(component0); }
public void remove(Component component0) {  uiBuilder.remove(component0); }
public Table table(String string0) { return uiBuilder.table(string0); }
public Table table(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.table(string0, valuechangelistener1); }
public void removeAll() {  uiBuilder.removeAll(); }
public void with(ComponentContainer componentcontainer0) {  uiBuilder.with(componentcontainer0); }
public void with(Application application0) {  uiBuilder.with(application0); }
public void with(Window window0) {  uiBuilder.with(window0); }
public Window window(String string0) { return uiBuilder.window(string0); }
public Label label(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.label(string0, valuechangelistener1); }
public Select select() { return uiBuilder.select(); }
public Select select(String string0) { return uiBuilder.select(string0); }
public Select select(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.select(string0, valuechangelistener1); }
public Tree tree(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.tree(string0, valuechangelistener1); }
public Tree tree() { return uiBuilder.tree(); }
public Tree tree(String string0) { return uiBuilder.tree(string0); }
public Embedded embedded(String string0) { return uiBuilder.embedded(string0); }
public Embedded embedded() { return uiBuilder.embedded(); }
public TextField textfield(String string0) { return uiBuilder.textfield(string0); }
public TextField textfield() { return uiBuilder.textfield(); }
public TextField textfield(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.textfield(string0, valuechangelistener1); }
public GridLayout gridlayout() { return uiBuilder.gridlayout(); }
public GridLayout gridlayout(String string0) { return uiBuilder.gridlayout(string0); }
public Link link() { return uiBuilder.link(); }
public Link link(String string0) { return uiBuilder.link(string0); }
public Panel panel(String string0) { return uiBuilder.panel(string0); }
public HorizontalLayout horizontallayout(String string0) { return uiBuilder.horizontallayout(string0); }
public VerticalLayout verticallayout(String string0) { return uiBuilder.verticallayout(string0); }
public Form form() { return uiBuilder.form(); }
public Form form(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.form(string0, valuechangelistener1); }
public Form form(String string0) { return uiBuilder.form(string0); }
public Button button() { return uiBuilder.button(); }
public Button button(String string0) { return uiBuilder.button(string0); }
public Button button(String string0, ClickListener clicklistener1) { return uiBuilder.button(string0, clicklistener1); }
public FormLayout formlayout() { return uiBuilder.formlayout(); }
public FormLayout formlayout(String string0) { return uiBuilder.formlayout(string0); }
public UriFragmentUtility urifragmentutility(String string0) { return uiBuilder.urifragmentutility(string0); }
public UriFragmentUtility urifragmentutility() { return uiBuilder.urifragmentutility(); }
public Accordion accordion(String string0) { return uiBuilder.accordion(string0); }
public Accordion accordion() { return uiBuilder.accordion(); }
public CheckBox checkbox() { return uiBuilder.checkbox(); }
public CheckBox checkbox(String string0) { return uiBuilder.checkbox(string0); }
public CheckBox checkbox(String string0, ClickListener clicklistener1) { return uiBuilder.checkbox(string0, clicklistener1); }
public SplitPanel splitpanel() { return uiBuilder.splitpanel(); }
public SplitPanel splitpanel(String string0) { return uiBuilder.splitpanel(string0); }
public PopupDateField popupdatefield(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.popupdatefield(string0, valuechangelistener1); }
public PopupDateField popupdatefield(String string0) { return uiBuilder.popupdatefield(string0); }
public PopupDateField popupdatefield() { return uiBuilder.popupdatefield(); }
public TwinColSelect twincolselect(String string0) { return uiBuilder.twincolselect(string0); }
public TwinColSelect twincolselect() { return uiBuilder.twincolselect(); }
public TwinColSelect twincolselect(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.twincolselect(string0, valuechangelistener1); }
public ListSelect listselect(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.listselect(string0, valuechangelistener1); }
public ListSelect listselect() { return uiBuilder.listselect(); }
public ListSelect listselect(String string0) { return uiBuilder.listselect(string0); }
public MenuBar menubar(String string0) { return uiBuilder.menubar(string0); }
public MenuBar menubar() { return uiBuilder.menubar(); }
public LoginForm loginform() { return uiBuilder.loginform(); }
public LoginForm loginform(String string0) { return uiBuilder.loginform(string0); }
public McWindow mcwindow() { return uiBuilder.mcwindow(); }
public McWindow mcwindow(String string0) { return uiBuilder.mcwindow(string0); }
public NativeSelect nativeselect(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.nativeselect(string0, valuechangelistener1); }
public NativeSelect nativeselect() { return uiBuilder.nativeselect(); }
public NativeSelect nativeselect(String string0) { return uiBuilder.nativeselect(string0); }
public InlineDateField inlinedatefield() { return uiBuilder.inlinedatefield(); }
public InlineDateField inlinedatefield(String string0) { return uiBuilder.inlinedatefield(string0); }
public InlineDateField inlinedatefield(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.inlinedatefield(string0, valuechangelistener1); }
public RichTextArea richtextarea(String string0) { return uiBuilder.richtextarea(string0); }
public RichTextArea richtextarea(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.richtextarea(string0, valuechangelistener1); }
public RichTextArea richtextarea() { return uiBuilder.richtextarea(); }
public OptionGroup optiongroup(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.optiongroup(string0, valuechangelistener1); }
public OptionGroup optiongroup() { return uiBuilder.optiongroup(); }
public OptionGroup optiongroup(String string0) { return uiBuilder.optiongroup(string0); }
public AbsoluteLayout absolutelayout(String string0) { return uiBuilder.absolutelayout(string0); }
public AbsoluteLayout absolutelayout() { return uiBuilder.absolutelayout(); }
public ProgressIndicator progressindicator(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.progressindicator(string0, valuechangelistener1); }
public ProgressIndicator progressindicator(String string0) { return uiBuilder.progressindicator(string0); }
public ProgressIndicator progressindicator() { return uiBuilder.progressindicator(); }
public Slider slider(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.slider(string0, valuechangelistener1); }
public Slider slider(String string0) { return uiBuilder.slider(string0); }
public Slider slider() { return uiBuilder.slider(); }
public ComboBox combobox(String string0) { return uiBuilder.combobox(string0); }
public ComboBox combobox() { return uiBuilder.combobox(); }
public ComboBox combobox(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.combobox(string0, valuechangelistener1); }
public DateField datefield(String string0) { return uiBuilder.datefield(string0); }
public DateField datefield(String string0, ValueChangeListener valuechangelistener1) { return uiBuilder.datefield(string0, valuechangelistener1); }
public DateField datefield() { return uiBuilder.datefield(); }
public TabSheet tabsheet(String string0) { return uiBuilder.tabsheet(string0); }
public TabSheet tabsheet() { return uiBuilder.tabsheet(); }
public void endWith() {  uiBuilder.endWith(); }
/* GENERATED DELEGATES END */

}
