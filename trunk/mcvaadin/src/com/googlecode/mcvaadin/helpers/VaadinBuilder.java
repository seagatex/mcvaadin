package com.googlecode.mcvaadin.helpers;

import java.util.Stack;

import com.googlecode.mcvaadin.McWindow;
import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
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

/** Generated class. Do not edit by hand. */
public class VaadinBuilder {
    protected ComponentContainer cc;
    private Stack<ComponentContainer> previousCCs = new Stack<ComponentContainer>();

    public VaadinBuilder(ComponentContainer cc) {
        this.cc = cc;
    }

    /** Create new FormLayout and add it to current component container. */
    public FormLayout formlayout() {
        FormLayout c = new FormLayout();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new FormLayout with given caption and add it to current component
     * container.
     */
    public FormLayout formlayout(String caption) {
        FormLayout c = formlayout();
        c.setCaption(caption);
        return c;
    }

    /** Create new UriFragmentUtility and add it to current component container. */
    public UriFragmentUtility urifragmentutility() {
        UriFragmentUtility c = new UriFragmentUtility();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new UriFragmentUtility with given caption and add it to current
     * component container.
     */
    public UriFragmentUtility urifragmentutility(String caption) {
        UriFragmentUtility c = urifragmentutility();
        c.setCaption(caption);
        return c;
    }

    /** Create new GridLayout and add it to current component container. */
    public GridLayout gridlayout() {
        GridLayout c = new GridLayout();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new GridLayout with given caption and add it to current component
     * container.
     */
    public GridLayout gridlayout(String caption) {
        GridLayout c = gridlayout();
        c.setCaption(caption);
        return c;
    }

    /** Create new HorizontalLayout and add it to current component container. */
    public HorizontalLayout horizontallayout() {
        HorizontalLayout c = new HorizontalLayout();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new HorizontalLayout with given caption and add it to current
     * component container.
     */
    public HorizontalLayout horizontallayout(String caption) {
        HorizontalLayout c = horizontallayout();
        c.setCaption(caption);
        return c;
    }

    /** Create new Accordion and add it to current component container. */
    public Accordion accordion() {
        Accordion c = new Accordion();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Accordion with given caption and add it to current component
     * container.
     */
    public Accordion accordion(String caption) {
        Accordion c = accordion();
        c.setCaption(caption);
        return c;
    }

    /** Create new TextField and add it to current component container. */
    public TextField textfield() {
        TextField c = new TextField();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new TextField with given caption and add it to current component
     * container.
     */
    public TextField textfield(String caption) {
        TextField c = textfield();
        c.setCaption(caption);
        return c;
    }

    /** Create new TextField with given caption and listener. */
    public TextField textfield(String caption,
            Property.ValueChangeListener changeListener) {
        TextField c = textfield(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new VerticalLayout and add it to current component container. */
    public VerticalLayout verticallayout() {
        VerticalLayout c = new VerticalLayout();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new VerticalLayout with given caption and add it to current
     * component container.
     */
    public VerticalLayout verticallayout(String caption) {
        VerticalLayout c = verticallayout();
        c.setCaption(caption);
        return c;
    }

    /** Create new Select and add it to current component container. */
    public Select select() {
        Select c = new Select();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Select with given caption and add it to current component
     * container.
     */
    public Select select(String caption) {
        Select c = select();
        c.setCaption(caption);
        return c;
    }

    /** Create new Select with given caption and listener. */
    public Select select(String caption,
            Property.ValueChangeListener changeListener) {
        Select c = select(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new Embedded and add it to current component container. */
    public Embedded embedded() {
        Embedded c = new Embedded();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Embedded with given caption and add it to current component
     * container.
     */
    public Embedded embedded(String caption) {
        Embedded c = embedded();
        c.setCaption(caption);
        return c;
    }

    /** Create new CheckBox and add it to current component container. */
    public CheckBox checkbox() {
        CheckBox c = new CheckBox();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new CheckBox with given caption and add it to current component
     * container.
     */
    public CheckBox checkbox(String caption) {
        CheckBox c = checkbox();
        c.setCaption(caption);
        return c;
    }

    /** Create new CheckBox with given caption and listener. */
    public CheckBox checkbox(String caption, Button.ClickListener listener) {
        CheckBox c = checkbox(caption);
        c.addListener(listener);
        return c;
    }

    /** Create new SplitPanel and add it to current component container. */
    public SplitPanel splitpanel() {
        SplitPanel c = new SplitPanel();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new SplitPanel with given caption and add it to current component
     * container.
     */
    public SplitPanel splitpanel(String caption) {
        SplitPanel c = splitpanel();
        c.setCaption(caption);
        return c;
    }

    /** Create new Tree and add it to current component container. */
    public Tree tree() {
        Tree c = new Tree();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Tree with given caption and add it to current component
     * container.
     */
    public Tree tree(String caption) {
        Tree c = tree();
        c.setCaption(caption);
        return c;
    }

    /** Create new Tree with given caption and listener. */
    public Tree tree(String caption, Property.ValueChangeListener changeListener) {
        Tree c = tree(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new Button and add it to current component container. */
    public Button button() {
        Button c = new Button();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Button with given caption and add it to current component
     * container.
     */
    public Button button(String caption) {
        Button c = button();
        c.setCaption(caption);
        return c;
    }

    /** Create new Button with given caption and listener. */
    public Button button(String caption, Button.ClickListener listener) {
        Button c = button(caption);
        c.addListener(listener);
        return c;
    }

    /** Create new PopupDateField and add it to current component container. */
    public PopupDateField popupdatefield() {
        PopupDateField c = new PopupDateField();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new PopupDateField with given caption and add it to current
     * component container.
     */
    public PopupDateField popupdatefield(String caption) {
        PopupDateField c = popupdatefield();
        c.setCaption(caption);
        return c;
    }

    /** Create new PopupDateField with given caption and listener. */
    public PopupDateField popupdatefield(String caption,
            Property.ValueChangeListener changeListener) {
        PopupDateField c = popupdatefield(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new Table and add it to current component container. */
    public Table table() {
        Table c = new Table();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Table with given caption and add it to current component
     * container.
     */
    public Table table(String caption) {
        Table c = table();
        c.setCaption(caption);
        return c;
    }

    /** Create new Table with given caption and listener. */
    public Table table(String caption,
            Property.ValueChangeListener changeListener) {
        Table c = table(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new TwinColSelect and add it to current component container. */
    public TwinColSelect twincolselect() {
        TwinColSelect c = new TwinColSelect();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new TwinColSelect with given caption and add it to current
     * component container.
     */
    public TwinColSelect twincolselect(String caption) {
        TwinColSelect c = twincolselect();
        c.setCaption(caption);
        return c;
    }

    /** Create new TwinColSelect with given caption and listener. */
    public TwinColSelect twincolselect(String caption,
            Property.ValueChangeListener changeListener) {
        TwinColSelect c = twincolselect(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new ListSelect and add it to current component container. */
    public ListSelect listselect() {
        ListSelect c = new ListSelect();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new ListSelect with given caption and add it to current component
     * container.
     */
    public ListSelect listselect(String caption) {
        ListSelect c = listselect();
        c.setCaption(caption);
        return c;
    }

    /** Create new ListSelect with given caption and listener. */
    public ListSelect listselect(String caption,
            Property.ValueChangeListener changeListener) {
        ListSelect c = listselect(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new Label and add it to current component container. */
    public Label label() {
        Label c = new Label();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Label with given caption and add it to current component
     * container.
     */
    public Label label(String caption) {
        Label c = label();
        c.setCaption(caption);
        return c;
    }

    /** Create new Label with given caption and listener. */
    public Label label(String caption,
            Property.ValueChangeListener changeListener) {
        Label c = label(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new MenuBar and add it to current component container. */
    public MenuBar menubar() {
        MenuBar c = new MenuBar();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new MenuBar with given caption and add it to current component
     * container.
     */
    public MenuBar menubar(String caption) {
        MenuBar c = menubar();
        c.setCaption(caption);
        return c;
    }

    /** Create new Link and add it to current component container. */
    public Link link() {
        Link c = new Link();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Link with given caption and add it to current component
     * container.
     */
    public Link link(String caption) {
        Link c = link();
        c.setCaption(caption);
        return c;
    }

    /** Create new Window and add it to current component container. */
    public Window window() {
        Window c = new Window();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Window with given caption and add it to current component
     * container.
     */
    public Window window(String caption) {
        Window c = window();
        c.setCaption(caption);
        return c;
    }

    /** Create new LoginForm and add it to current component container. */
    public LoginForm loginform() {
        LoginForm c = new LoginForm();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new LoginForm with given caption and add it to current component
     * container.
     */
    public LoginForm loginform(String caption) {
        LoginForm c = loginform();
        c.setCaption(caption);
        return c;
    }

    /** Create new McWindow and add it to current component container. */
    public McWindow mcwindow() {
        McWindow c = new McWindow();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new McWindow with given caption and add it to current component
     * container.
     */
    public McWindow mcwindow(String caption) {
        McWindow c = mcwindow();
        c.setCaption(caption);
        return c;
    }

    /** Create new NativeSelect and add it to current component container. */
    public NativeSelect nativeselect() {
        NativeSelect c = new NativeSelect();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new NativeSelect with given caption and add it to current
     * component container.
     */
    public NativeSelect nativeselect(String caption) {
        NativeSelect c = nativeselect();
        c.setCaption(caption);
        return c;
    }

    /** Create new NativeSelect with given caption and listener. */
    public NativeSelect nativeselect(String caption,
            Property.ValueChangeListener changeListener) {
        NativeSelect c = nativeselect(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new InlineDateField and add it to current component container. */
    public InlineDateField inlinedatefield() {
        InlineDateField c = new InlineDateField();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new InlineDateField with given caption and add it to current
     * component container.
     */
    public InlineDateField inlinedatefield(String caption) {
        InlineDateField c = inlinedatefield();
        c.setCaption(caption);
        return c;
    }

    /** Create new InlineDateField with given caption and listener. */
    public InlineDateField inlinedatefield(String caption,
            Property.ValueChangeListener changeListener) {
        InlineDateField c = inlinedatefield(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new RichTextArea and add it to current component container. */
    public RichTextArea richtextarea() {
        RichTextArea c = new RichTextArea();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new RichTextArea with given caption and add it to current
     * component container.
     */
    public RichTextArea richtextarea(String caption) {
        RichTextArea c = richtextarea();
        c.setCaption(caption);
        return c;
    }

    /** Create new RichTextArea with given caption and listener. */
    public RichTextArea richtextarea(String caption,
            Property.ValueChangeListener changeListener) {
        RichTextArea c = richtextarea(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new OptionGroup and add it to current component container. */
    public OptionGroup optiongroup() {
        OptionGroup c = new OptionGroup();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new OptionGroup with given caption and add it to current component
     * container.
     */
    public OptionGroup optiongroup(String caption) {
        OptionGroup c = optiongroup();
        c.setCaption(caption);
        return c;
    }

    /** Create new OptionGroup with given caption and listener. */
    public OptionGroup optiongroup(String caption,
            Property.ValueChangeListener changeListener) {
        OptionGroup c = optiongroup(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new AbsoluteLayout and add it to current component container. */
    public AbsoluteLayout absolutelayout() {
        AbsoluteLayout c = new AbsoluteLayout();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new AbsoluteLayout with given caption and add it to current
     * component container.
     */
    public AbsoluteLayout absolutelayout(String caption) {
        AbsoluteLayout c = absolutelayout();
        c.setCaption(caption);
        return c;
    }

    /** Create new Panel and add it to current component container. */
    public Panel panel() {
        Panel c = new Panel();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Panel with given caption and add it to current component
     * container.
     */
    public Panel panel(String caption) {
        Panel c = panel();
        c.setCaption(caption);
        return c;
    }

    /** Create new ProgressIndicator and add it to current component container. */
    public ProgressIndicator progressindicator() {
        ProgressIndicator c = new ProgressIndicator();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new ProgressIndicator with given caption and add it to current
     * component container.
     */
    public ProgressIndicator progressindicator(String caption) {
        ProgressIndicator c = progressindicator();
        c.setCaption(caption);
        return c;
    }

    /** Create new ProgressIndicator with given caption and listener. */
    public ProgressIndicator progressindicator(String caption,
            Property.ValueChangeListener changeListener) {
        ProgressIndicator c = progressindicator(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new Slider and add it to current component container. */
    public Slider slider() {
        Slider c = new Slider();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Slider with given caption and add it to current component
     * container.
     */
    public Slider slider(String caption) {
        Slider c = slider();
        c.setCaption(caption);
        return c;
    }

    /** Create new Slider with given caption and listener. */
    public Slider slider(String caption,
            Property.ValueChangeListener changeListener) {
        Slider c = slider(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new ComboBox and add it to current component container. */
    public ComboBox combobox() {
        ComboBox c = new ComboBox();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new ComboBox with given caption and add it to current component
     * container.
     */
    public ComboBox combobox(String caption) {
        ComboBox c = combobox();
        c.setCaption(caption);
        return c;
    }

    /** Create new ComboBox with given caption and listener. */
    public ComboBox combobox(String caption,
            Property.ValueChangeListener changeListener) {
        ComboBox c = combobox(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new Form and add it to current component container. */
    public Form form() {
        Form c = new Form();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new Form with given caption and add it to current component
     * container.
     */
    public Form form(String caption) {
        Form c = form();
        c.setCaption(caption);
        return c;
    }

    /** Create new Form with given caption and listener. */
    public Form form(String caption, Property.ValueChangeListener changeListener) {
        Form c = form(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new DateField and add it to current component container. */
    public DateField datefield() {
        DateField c = new DateField();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new DateField with given caption and add it to current component
     * container.
     */
    public DateField datefield(String caption) {
        DateField c = datefield();
        c.setCaption(caption);
        return c;
    }

    /** Create new DateField with given caption and listener. */
    public DateField datefield(String caption,
            Property.ValueChangeListener changeListener) {
        DateField c = datefield(caption);
        c.addListener(changeListener);
        return c;
    }

    /** Create new TabSheet and add it to current component container. */
    public TabSheet tabsheet() {
        TabSheet c = new TabSheet();
        c.setImmediate(true);
        add(c);
        return c;
    }

    /**
     * Create new TabSheet with given caption and add it to current component
     * container.
     */
    public TabSheet tabsheet(String caption) {
        TabSheet c = tabsheet();
        c.setCaption(caption);
        return c;
    }

    /** Move the build 'focus' of this builder to window's content. */
    public void with(Window w) {
        with(w.getContent());
    }

    /**
     * Move the build 'focus' of this builder to applications main window's
     * content.
     */
    public void with(Application a) {
        with(a.getMainWindow());
    }

    /**
     * Move the build 'focus' of this builder to another component container.
     * /** Previously focused layout is rememered in a stack and that can be
     * refocused using
     */
    public void with(ComponentContainer cc) {
        with(cc, true);
    }

    /** Move the build 'focus' of this builder to another component container. */
    protected void with(ComponentContainer cc, boolean addToStack) {
        if (addToStack && this.cc != null && cc != this.cc) {
            previousCCs.push(this.cc);
        }
        this.cc = cc;
    }

    /** Add component to current component container. */
    public void add(Component c) {
        if (c != null && !(c instanceof Window)) {
            cc.addComponent(c);
        } else if (c instanceof Window) {
            Window parentWin = cc.getWindow();
            if (parentWin != null && parentWin.getParent() != null) {
                parentWin = parentWin.getParent().getWindow();
            }
            parentWin.addWindow((Window) c);
        }
    }

    /** Remove component from current component container. */
    public void remove(Component c) {
        if (c != null) {
            cc.removeComponent(c);
        }
    }

    /** Remove all components from current component container. */
    public void removeAll() {
        cc.removeAllComponents();
    }

    /** Move build focus to previous component container. */
    public void endWith() {
        with(previousComponentContainer(), false);
    }

    /**
     * Get that previously focused layout and remove it from stack. If the
     * layout stack is empty current layout is returned.
     */
    protected ComponentContainer previousComponentContainer() {
        if (previousCCs.isEmpty()) {
            return cc;
        }
        return previousCCs.pop();
    }
}
