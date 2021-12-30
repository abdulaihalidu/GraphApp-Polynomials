import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AppFrame extends JFrame {
    // a panel to plot the graph
    GraphPanel graph_area;
    // A label to display which polynomial is currently plotted
    JLabel Px;
    // textBoxes
    JTextArea function;
    JTextArea start_point;
    // buttons
    JButton plot_button;
    JButton next;
    JButton previous;
    // checkbox
    JCheckBox check_box;
    // control variables
    Boolean plt = false;
    Boolean next_poly = false;
    Boolean pre_poly = false;
    Boolean checked = false; // control to the checkbox
    int cnt = -1;

    // variables to keep values obtained from the various text fields
    static String str = "";  // represents the function entered by the user
    int start_p = 0;     // the point at which the series expansion is made. Defaults to 0

    // Reference points
    int xRef = 360;
    int yRef = 290;

    // an array to hold the derivatives of the function.
    static private final int N = 10; // I am limiting the number of derivatives to 10  due to my PC's computing power.
    static Exp[] derivatives = new Exp[N];

    public AppFrame() {

        graph_area = new GraphPanel();
        graph_area.setBounds(0, 0, 720, 580);
        // polynomial currently plotted
        Px = new JLabel("Px");
        Px.setFont(new Font("", Font.BOLD, 20));
        Px.setBounds(345, 580, 40, 40);
        // textArea to get function from user
        JLabel f = new JLabel("f(x) =");
        f.setBounds(20, 650, 50, 20);
        f.setLabelFor(function);
        function = new JTextArea();
        function.setBounds(60, 650, 300, 20);
        // textArea to get the point at which the series will be expanded, from the user
        JLabel s = new JLabel("X0 =");
        s.setBounds(20, 700, 50, 20);
        start_point = new JTextArea();
        start_point.setText("0");
        start_point.setBounds(60, 700, 300, 20);

        // checkbox
        JLabel c = new JLabel();
        c.setBounds(430, 590, 150,50);
        c.setText("plot over graph");
        check_box = new JCheckBox();
        check_box.setBounds(520, 590, 50, 50);

        // plot graph button
        plot_button = new JButton();
        plot_button.setBounds(570, 590, 100, 35);
        plot_button.setText("Plot graph");
        plot_button.setFocusPainted(false);
        plot_button.setBackground(Color.cyan);

        // plot previous polynomial button
        previous = new JButton();
        previous.setBounds(565, 680, 50, 35);
        previous.setText("<");
        previous.setFocusPainted(false);
        previous.setBackground(Color.red);
        previous.setEnabled(false);

        // plot next polynomial button
        next = new JButton();
        next.setBounds(625, 680, 50, 35);
        next.setText(">");
        next.setFocusPainted(false);
        next.setBackground(Color.green);
        next.setEnabled(false);

        this.setTitle("Graphs of Polynomials.");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(720, 1080);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);

        this.add(graph_area);
        this.add(Px);
        this.add(plot_button);
        this.add(next);
        this.add(previous);
        this.add(function);
        this.add(start_point);
        this.add(check_box);
        this.add(f);  // label for function to be entered by user
        this.add(s);  // label for point about which the series expansion is made
        this.add(c);  // label for checkbox

        // action listener for the various buttons
        ButtonHandler handler = new ButtonHandler();
        plot_button.addActionListener(handler);
        next.addActionListener(handler);
        previous.addActionListener(handler);

        // item listener for the checkbox
        check_box.addItemListener(new CheckBoxHandler());
    }
    private class GraphPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setForeground(Color.blue);
            setBackground(Color.white);
            // coordinate lines
            g.drawLine(120,290, 600,290);
            g.drawLine(360,15, 360,565);
            // west arrow
            g.drawLine(120, 290, 125, 285);
            g.drawLine(120, 290, 125, 295);
            // east arrow
            g.drawLine(595, 285, 600, 290);
            g.drawLine(595, 295, 600, 290);
            // north arrow
            g.drawLine(355, 20, 360, 15);
            g.drawLine(365, 20, 360, 15);
            // south arrow
            g.drawLine(355, 560, 360, 565);
            g.drawLine(365, 560, 360, 565);

            // X-axis labelling
            g.drawLine(180, 285, 180, 295);
            g.drawString("-12", 170, 305);

            g.drawLine(240, 285, 240, 295);
            g.drawString("-8", 230, 305);

            g.drawLine(300, 285, 300, 295);
            g.drawString("-4", 290, 305);

            g.drawString("0", 350, 305);

            g.drawLine(420, 285, 420, 295);
            g.drawString("4", 410, 305);

            g.drawLine(480, 285, 480, 295);
            g.drawString("8", 470, 305);

            g.drawLine(540, 285, 540, 295);
            g.drawString("12", 530, 305);

            // Y-axis labelling
            g.drawLine(355, 70, 365, 70);
            g.drawString("20", 340, 75);

            g.drawLine(355, 125, 365, 125);
            g.drawString("15", 340, 130);

            g.drawLine(355, 180, 365, 180);
            g.drawString("10", 340, 185);

            g.drawLine(355, 235, 365, 235);
            g.drawString("5", 345, 240);

            g.drawLine(355, 345, 365, 345);
            g.drawString("-5", 340, 350);

            g.drawLine(355, 400, 365, 400);
            g.drawString("-10", 335, 405);

            g.drawLine(355, 455, 365, 455);
            g.drawString("-15", 335, 460);

            g.drawLine(355, 510, 365, 510);
            g.drawString("-20", 335, 515);

            if(plt){
                // plotting f(x)
                for(int i =-12; i< 12; i++){
                    double a = (Double) new EvalVisitor(i).visit(derivatives[0]);
                    double b = (Double) new EvalVisitor(i+1).visit(derivatives[0]);
                    g.drawLine(xRef+(i*15), yRef-(int)(a*11), xRef+((i+1)*15), yRef-(int)(b*11));
                }
            }
            plotPx(g, next_poly); // check if next polynomial is to be plotted
            plotPx(g, pre_poly);  // check if previous polynomial is to be plotted
        }
        // this function plots the polynomial px, where x = 0, 1, 2, .....
        private void plotPx(Graphics g, Boolean px) {
            if(px){
                Px.setText("P"+cnt);
                if(checked){
                    for(int i = 0; i<=cnt; i++){
                        drawPx(g, i);
                    }
                }
                else {
                    for(int i = 0; i<=cnt; i++){
                        if(i == cnt){drawPx(g, i);}
                    }
                }
            }
        }
        public void drawPx(Graphics g, int px){
            //Allocate the size of the array
            int s = 7; // size of color array
            Color[] colors = new Color[s];
            //Initialize the values of the array
            colors[0] = Color.red;
            colors[1] = Color.black;
            colors[2] = Color.pink;
            colors[3] = Color.orange;
            colors[4] = Color.green;
            colors[5] = Color.magenta;
            colors[6] = Color.cyan;
            // an array to keep the values of the various derivatives.
            double[] fx = new double[N];
            // calculating the values of f(x)^n
            for(int i = 0; i < N; i++){
                try {
                    fx[i] = (Double) new EvalVisitor(start_p).visit(derivatives[i]);
                } catch (Exception en) {
                    System.out.println("Syntax error: " + en.getMessage());
                }
            }
            // an array to keep the calculated y values of the polynomial Px,  where x = 0, 1, 2, .....
            double[] points = new double[25];
            for(int x = -12; x <= 12; x++){
                double a = 0;
                for(int i = 0; i<=px; i++){
                    a += fx[i] * (Math.pow((x-start_p), i))/fact(i);
                }
                points[x+12] = a;
            }
            g.setColor(colors[px%s]);
            for(int i=-12; i<12; i++){
                try{
                    double a = points[i+12];
                    double b = points[i+13];
                    g.drawLine(xRef+(i*15), yRef-(int)(a*11), xRef+((i+1)*15), yRef-(int)(b*11));
                }catch (Exception e){
                    System.out.println("plotted on a dirty area!");
                }
            }
        }
    }
    public void setParams() {
        try {
            str = function.getText();
            start_p = Integer.parseInt(start_point.getText());

            next.setEnabled(true);
        } catch (Exception exe) {
            System.out.println("Check that function and start_point values are entered!");
        }
    }
    public void getDerivatives(){
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        Exp e = null;
        try {
            e =  new FuncParser(stream).Start();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        derivatives[0] = e;
        for(int i=0; i<N -1; i++){
            assert derivatives[i] != null;
            derivatives[i+1] = (Exp) new DeriveVisitor("x").visit(derivatives[i]);
        }
    }
    public long  fact(int n) {
        int i, fac = 1;
        for (i = 1; i <= n; i++) {
            fac *=  i;
        }
        return fac;
    }
    private class ButtonHandler implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == plot_button){
                plt = true;
                setParams();
                getDerivatives();
                graph_area.repaint();
            }
            else if(e.getSource() == next){
                if(cnt < N-1){
                    pre_poly = false;
                    next_poly = true;
                    cnt += 1;
                    previous.setEnabled(true);
                    if(cnt == N-1){next.setEnabled(false);}
                    graph_area.repaint();
                }
            }
            else if(e.getSource() == previous){
                next_poly = false;
                pre_poly = true;
                if(cnt >= 0 ){
                    cnt -= 1;
                    next.setEnabled(true);
                    graph_area.repaint();
                    if(cnt < 0){
                        pre_poly = false;
                        previous.setEnabled(false);
                        Px.setText("Px");
                    }
                }
            }
        }
    }
    private class CheckBoxHandler implements ItemListener {
        public void itemStateChanged(ItemEvent event){
            checked = check_box.isSelected();
        }
    }

}
