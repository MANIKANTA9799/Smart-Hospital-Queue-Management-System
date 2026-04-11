import java.awt.*; // awt
import java.awt.event.*; // event
import java.util.*; // swing
import javax.swing.*; // collections

// encapsulation
class Person {
    String name;
    int age;
    int weight;
    int height;
    String disease;

    Person(String n,int a,int w,int h,String d){
        name=n;
        age=a;
        weight=w;
        height=h;
        disease=d;
    }
}

// encapsulation
class Token {
    int id;
    int priority;
    long time;
    Person p;

    Token(int i,Person p1){
        id=i;
        p=p1;
        time=System.currentTimeMillis();
        priority=calcPriority(p1);
    }

    // abstraction
    int calcPriority(Person p){
        int pr=1;

        if(p.disease.equals("accident")) pr=5;
        else if(p.disease.equals("brain")) pr=4;
        else if(p.disease.equals("heart")) pr=4;
        else if(p.disease.equals("fracture")) pr=3;
        else if(p.disease.equals("fever")) pr=1;

        if(p.age > 60) pr += 1;

        // formula
        double bmi = p.weight / Math.pow(p.height / 100.0, 2);

        if(bmi > 30) pr += 2;
        else if(bmi > 25) pr += 1;

        return pr;
    }
}

// composition
class QueueManager {

    // priorityqueue
    PriorityQueue<Token> pq;
    int counter=1;

    QueueManager(){
        // comparator
        pq=new PriorityQueue<>((a,b)->{
            if(a.priority==b.priority)
                return (int)(a.time-b.time);
            return b.priority-a.priority;
        });
    }

    void add(Person p){
        pq.add(new Token(counter++,p));
    }

    Token serve(){
        return pq.poll();
    }

    ArrayList<Token> getAll(){
        return new ArrayList<>(pq);
    }
}

// inheritance
public class MainApp extends JFrame implements ActionListener {

    JTextField nameField,ageField,weightField,heightField;
    JComboBox<String> diseaseBox;

    JButton addBtn,serveBtn,clearBtn;

    JTextArea area;

    JLabel idLabel,nameLabel,typeLabel;

    JPanel currentPanel;

    QueueManager qm;

    MainApp(){

        qm=new QueueManager();

        setTitle("Smart Hospital Queue");
        setSize(850,600);

        // layout
        setLayout(new BorderLayout(10,10));

        JPanel top=new JPanel(new GridLayout(2,5,10,10));

        nameField=new JTextField();
        ageField=new JTextField();
        weightField=new JTextField();
        heightField=new JTextField();

        diseaseBox=new JComboBox<>(new String[]{
            "fever","fracture","heart","brain","accident"
        });

        top.add(new JLabel("Name"));
        top.add(new JLabel("Age"));
        top.add(new JLabel("Weight (kg)"));
        top.add(new JLabel("Height (cm)"));
        top.add(new JLabel("Disease"));

        top.add(nameField);
        top.add(ageField);
        top.add(weightField);
        top.add(heightField);
        top.add(diseaseBox);

        // textarea
        area=new JTextArea();
        area.setFont(new Font("Monospaced",Font.BOLD,14));
        area.setEditable(false);

        JScrollPane sp=new JScrollPane(area);

        // panel
        currentPanel=new JPanel();
        currentPanel.setLayout(new BoxLayout(currentPanel,BoxLayout.Y_AXIS));
        currentPanel.setBorder(BorderFactory.createTitledBorder("Now Serving"));
        currentPanel.setPreferredSize(new Dimension(220,180));

        idLabel=new JLabel("ID: -");
        nameLabel=new JLabel("Name: -");
        typeLabel=new JLabel("Disease: -");

        idLabel.setFont(new Font("Arial",Font.BOLD,18));
        nameLabel.setFont(new Font("Arial",Font.PLAIN,15));
        typeLabel.setFont(new Font("Arial",Font.PLAIN,15));

        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        currentPanel.add(Box.createVerticalStrut(15));
        currentPanel.add(idLabel);
        currentPanel.add(Box.createVerticalStrut(10));
        currentPanel.add(nameLabel);
        currentPanel.add(Box.createVerticalStrut(5));
        currentPanel.add(typeLabel);

        JPanel rightWrap=new JPanel(new BorderLayout());
        rightWrap.add(currentPanel,BorderLayout.NORTH);

        JPanel bottom=new JPanel();

        addBtn=new JButton("Add");
        serveBtn=new JButton("Serve");
        clearBtn=new JButton("Clear");

        bottom.add(addBtn);
        bottom.add(serveBtn);
        bottom.add(clearBtn);

        add(top,BorderLayout.NORTH);
        add(sp,BorderLayout.CENTER);
        add(rightWrap,BorderLayout.EAST);
        add(bottom,BorderLayout.SOUTH);

        // eventhandling
        addBtn.addActionListener(this);
        serveBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void refresh(){

        ArrayList<Token> list=qm.getAll();

        // sorting
        list.sort((a,b)->{
            if(a.priority==b.priority)
                return (int)(a.time-b.time);
            return b.priority-a.priority;
        });

        StringBuilder sb=new StringBuilder();

        sb.append(String.format("%-5s %-12s %-5s %-10s %-12s %-10s\n",
                "ID","NAME","AGE","WT(kg)","DISEASE","PRIORITY"));
        sb.append("-------------------------------------------------------------\n");

        for(Token t:list){
            sb.append(String.format("%-5s %-12s %-5d %-10d %-12s %-10d\n",
                    "T"+t.id,
                    t.p.name,
                    t.p.age,
                    t.p.weight,
                    t.p.disease,
                    t.priority));
        }

        area.setText(sb.toString());
    }

    // colorlogic
    Color getColor(String disease){
        if(disease.equals("accident")) return new Color(255,102,102);
        if(disease.equals("heart")) return new Color(255,178,102);
        if(disease.equals("brain")) return new Color(204,153,255);
        if(disease.equals("fracture")) return new Color(255,255,153);
        return new Color(153,255,153);
    }

    // polymorphism
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==addBtn){
            try{
                Person p=new Person(
                    nameField.getText(),
                    Integer.parseInt(ageField.getText().trim()),
                    Integer.parseInt(weightField.getText().trim()),
                    Integer.parseInt(heightField.getText().trim()),
                    (String)diseaseBox.getSelectedItem()
                );

                qm.add(p);
                refresh();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,"invalid input");
            }
        }

        else if(e.getSource()==serveBtn){
            Token t=qm.serve();

            if(t!=null){
                idLabel.setText("ID: T"+t.id);
                nameLabel.setText("Name: "+t.p.name);
                typeLabel.setText("Disease: "+t.p.disease);

                currentPanel.setBackground(getColor(t.p.disease));
            }

            refresh();
        }

        else{
            qm=new QueueManager();
            idLabel.setText("ID: -");
            nameLabel.setText("Name: -");
            typeLabel.setText("Disease: -");
            currentPanel.setBackground(null);
            refresh();
        }
    }

    public static void main(String[] args){
        new MainApp();
    }
}