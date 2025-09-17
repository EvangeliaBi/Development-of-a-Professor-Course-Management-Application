package exetastiki_earino;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Results extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel contentPane;
    private JTextField txt_ID;
    private JTextField txt_Name;
    private JTextField txt_LName;   
    private ResultSet rsData; 	     // Το scrollable και updatable ResultSet.

     // Εκτέλεση παραθύρου από την μέθοδο main.
   // public static void main(String[] args) {
    //  EventQueue.invokeLater(new Runnable() {
       //   public void run() {
         //     try {
              //    Results frame = new Results();
                //  frame.setMinimumSize(new Dimension(450, 300));		// Ορίζει τις ελάχιστες διαστάσεις του παραθύρου (450 pixels πλάτος και 300 pixels ύψος) για να εξασφαλιστεί ότι το περιεχόμενο θα εμφανίζεται σωστά.
                //  frame.setLocationRelativeTo(null);					// Κεντράρει το παράθυρο στην οθόνη.
               //   frame.setVisible(true);								// Γίνεται ορατό το παράθυρο της φόρμας αποτελεσμάτων.
            //  } catch (Exception e) {
               //   e.printStackTrace();
            //  }
        //  }
    //  });
  //  }

    // Δημιουργία παραθύρου.
    public Results() {										// Μέσα στο σώμα του constructor πραγματοποιείται η γραφική διαμόρφωση του παραθύρου.
      setBackground(new Color(245, 245, 250));
      addWindowListener(new WindowAdapter() {				// Προσθήκη WindowListener για παρακολούθηση του WindowEvent.
          @Override
          public void windowClosing(WindowEvent e) {
              Main_Application.insert.setEnabled(true);		// Όταν ο χρήστης πατήσει το κουμπί X για να βγει από το παράθυρο των αποτελεσμάτων(το τρέχον παράθυρο) ενεργοποιείται ξανά το προηγούμενο παράθυρο μέσω της μεθόδου setEnabled(true) που είναι το παράθυρο(Search_Insert).
          }
      });
      setTitle("Results");
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		// Όταν το παράθυρο κλείνει, θα αποκρύπτεται αλλά δεν θα καταστρέφεται (επιτρέποντας την επανενεργοποίηση του χωρίς να χρειάζεται ξανά η δημιουργία του).
      setBounds(100, 100, 450, 300);						// Καθορισμός συντεταγμένων(x,y) και πλάτους και ύψους του παραθύρου.
      
      contentPane = new JPanel();							// Δημιουργία panel που θα λειτουργεί ως κύριο container.
      contentPane.setBackground(new Color(245, 245, 250));
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		// Προσθέτει ένα κενό περιθώριο 5 pixels από όλες τις πλευρές.
      contentPane.setLayout(new BorderLayout());				// Καθορισμός της διάταξης του contentPane με BorderLayout(=τοποθέτηση των στοιχείων σε ζώνες).
      setContentPane(contentPane);							// Ορίζει το contentPane ως το κύριο πάνελ του παραθύρου.
      
      // Header στο NORTH με το pnlNorth, ένα JPanel με FlowLayout κεντραρισμένο (FlowLayout.CENTER), προκειμένου τα στοιχεία που θα προσθέσουμε σε αυτό να τοποθετηθούν οριζόντια στο κέντρο.
      JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));		// Το συγκεκριμένο pnlNorth(βόρειο πάνελ) έχει ως διαχειριστή διάταξης το FlowLayout(που επεκτείνει την κλάση Object και υλοποιεί/implements το LayoutManager), καθώς αποτελεί ένα αντικείμενο της κλάσης FlowLayout του πακέτου java.awt, όπου η τοποθέτηση των συστατικών πραγματοποιείται από αριστερά προς τα δεξιά κι από επάνω προς τα κάτω. Αυτό σημαίνει ότι υπάρχει συνεχόμενη τοποθέτηση των συστατικών από το επάνω αριστερό άκρο της πρώτης σειράς μέχρι το δεξιό άκρο της εφαρμογής ενώ ταυτόχρονα όσα components περισσέψουν πραγματοποιείται τοποθέτηση στην επόμενη σειρά(δηλαδή από κάτω) πάλι από τα αριστερά προς τα δεξιά μέχρι την πλήρη τοποθέτηση όλων των συστατικών. Δημιουργία ενός JPanel με τοποθέτηση συστατικού North και πιο συγκεκριμένα στο πάνω μέρος του παραθύρου(λόγω και του BorderLayout που διαθέτει το κύριο container), για την τοποθέτηση του header της ετικέτας με εφαρμογή του alignment.center(για το κεντράρισμα) μέσα στο panel καθώς και με hgap(οριζόντιο κενό μεταξύ των στοιχείων στα 5 pixels) και vgap(κάθετο κενό στα 5 pixels σε περίπτωση που απαιτηθεί η διαμόρφωση περισσότερων σειρών για τα στοιχεία).
      pnlNorth.setBackground(new Color(245, 245, 250));
      JLabel lblResults = new JLabel("Results");				// Δημιουργία ετικέτας JLabel(=lblResults).
      lblResults.setBackground(new Color(153, 0, 102));
      lblResults.setForeground(new Color(0, 51, 102));
      lblResults.setFont(new Font("Tahoma", Font.PLAIN, 20));
      pnlNorth.add(lblResults);						// Προσθήκη της ετικέτας(lblResults) στο βόρειο πάνελ(pnlNorth).
      contentPane.add(pnlNorth, BorderLayout.NORTH);		// Τοποθέτηση του panel στην περιοχή North του contentPane σύμφωνα και με το BorderLayout.
      
      // Δημιουργούμε ένα panel (fieldsPanel) με GridLayout (3 rows, 2 columns) ώστε τα labels και τα JTextFields να στοιχίζονται ομαλά. Θέτουμε 10 pixels gap μεταξύ των στηλών και των γραμμών.
      JPanel fieldsPanel = new JPanel();						// Δημιουργούμε ένα νέο JPanel ονόματι fieldsPanel για την τοποθέτηση των labels και των αντίστοιχων text fields.
      fieldsPanel.setOpaque(false);								// Για το νέο JPanel(fieldsPanel) μέσω της μεθόδου setOpaque(η οποία κληρονομείται από την JComponent) εξασφαλίζεται η διαφάνεια του panel διατηρώντας το φόντο του γονικού στοιχείου(δηλαδή το background χρώμα του contentPane).
      fieldsPanel.setLayout(new GridLayout(3, 2, 10, 10));		// Με τρέχον αντικείμενο το fieldsPanel μέσω της μεθόδου setLayout τίθεται διαχειριστής διάταξης GridLayout δημιουργώντας ένα πλέγμα 3 γραμμών και 2 στηλών με 10 pixels οριζόντια και 10 pixels κατακόρυφη απόσταση μεταξύ των στοιχείων.
      
      // Δημιουργούμε τα Labels με σταθερό preferred μέγεθος ώστε να στοιχίζονται σωστά.
      Dimension labelSize = new Dimension(80, 25);			// Σε αυτό το σημείο δημιουργείται ένα καινούργιο αντικείμενο με όνομα labelSize τύπου Dimension καλώντας τον αντίστοιχο κατασκευαστή(Dimension μέσω του οποίου γίνεται αρχικοποίηση σε προτιμώμενο πλάτος και ύψος) και ορίζοντας το προτιμώμενο μέγεθος (80 pixels πλάτος και 25 pixels ύψος) για όλα τα labels θα εφαρμόζεται αυτό. 
      
      JLabel lblID = new JLabel("ID:", SwingConstants.RIGHT);		// Δημιουργία JLabel και χρήση της διεπαφής SwingConstants με καθορισμό σταθεράς το RIGHT(το οποίο έχει να κάνει με την στοίχηση του κειμένου στα δεξιά).
      lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblID.setForeground(new Color(0,102,153));
      lblID.setPreferredSize(labelSize);					// Μέσω αυτής της μεθόδου(setPreferredSize) για το τρέχον αντικείμενο(lblID) και βάζοντας ως παράμετρο εισόδου το (labelSize) διασφαλίζεται ότι η ετικέτα ID θα πάρει τις προτινόμενες διαστάσεις(80, 25) που ορίστηκαν για το αντικείμενο labelSize που είναι τύπου Dimension.
      fieldsPanel.add(lblID);								// Προσθήκη του (lblID-ετικέτα ID) μέσα στο fieldsPanel.
      
      txt_ID = new JTextField(10);							// Δημιουργία JTextField με 10 στήλες.
      txt_ID.setEditable(false);							// Μέσω της μεθόδου setEditable με τιμή false δεν δύνανται ο χρήστης του προγράμματος να επεξεργαστεί αυτή την περιοχή, δηλαδή αυτό το ID καθώς θα συμπληρώνεται αυτόματα από τον SQLServer θα τοποθετεί δηλαδή ένα κανούργιο μοναδικό ID (λόγω και του Auto Increment που θέσαμε στον SQLServer για το πεδίο ID) χωρίς να είναι ευθύνη του προγραμματιστή.
      txt_ID.setFont(new Font("Tahoma", Font.PLAIN, 14));
      txt_ID.setBackground(new Color(230,230,240));
      Dimension fieldSize = new Dimension(120, 25);			// Σε αυτό το σημείο δημιουργείται ένα καινούργιο αντικείμενο με όνομα fieldSize τύπου Dimension καλώντας τον αντίστοιχο κατασκευαστή(Dimension μέσω του οποίου γίνεται αρχικοποίηση σε προτιμώμενο πλάτος και ύψος) και ορίζοντας το προτιμώμενο μέγεθος (120 pixels πλάτος και 25 pixels ύψος) για όλα τα JTextFields θα εφαρμόζεται αυτό. Καθορισμός του προτινώμενου μεγέθους(setPreferredSize) και του μέγιστου μεγέθους(setMaximumSize) για το ίδιο το αντικείμενο Dimension(120 pixels πλάτος και 25 pixels ύψος), εξασφαλίζοντας ότι το text field δεν θα ξεπεράσει τις διαστάσεις αυτές.
      txt_ID.setPreferredSize(fieldSize);					// Μέσω αυτής της μεθόδου(setPreferredSize) για το τρέχον αντικείμενο(txt_ID) και βάζοντας ως παράμετρο εισόδου της μεθόδου το (fieldSize) διασφαλίζεται ότι το JTextField ID θα πάρει τις προτινόμενες διαστάσεις που ορίστηκαν για το αντικείμενο fieldSize που είναι τύπου Dimension.
      txt_ID.setMaximumSize(fieldSize);
      fieldsPanel.add(txt_ID);							// Προσθήκη του πεδίου κειμένου(txt_ID) μέσα στο fieldsPanel.
      
      JLabel lblName = new JLabel("Name:", SwingConstants.RIGHT);		// Δημιουργία JLabel και χρήση της διεπαφής SwingConstants με καθορισμό σταθεράς το RIGHT(το οποίο έχει να κάνει με την στοίχηση του κειμένου στα δεξιά).
      lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblName.setForeground(new Color(0,102,153));
      lblName.setPreferredSize(labelSize);				// Καθορισμός προτινώμενων διαστάσεων μέσω χρήσης της μεθόδου setPreferredSize και βάζοντας ως παράμετρο εισόδου το αντικέιμενο (labelSize) που φτιάξαμε παραπάνω, προκειμένου να εφαρμόσει τις προτεινόμενες διαστάσεις για την ετικέτα lblName.
      fieldsPanel.add(lblName);							// Προσθήκη της ετικέτας(lblName) μέσα στο fieldsPanel.
      
      txt_Name = new JTextField(10);
      txt_Name.setFont(new Font("Tahoma", Font.PLAIN, 14));
      txt_Name.setBackground(new Color(230,230,240));
      txt_Name.setPreferredSize(fieldSize);			// Καθορισμός των προτινόμενων διαστάσεων δηλώνοντας ως παράμετρο εισόδου το fieldSize(το αντικείμενο που φτιάξαμε παραπάνω) και είναι τύπου Dimension.
      txt_Name.setMaximumSize(fieldSize);
      fieldsPanel.add(txt_Name);					// Προσθήκη του txt_Name μέσα στο fieldsPanel.
      
      JLabel lblLName = new JLabel("Last Name:", SwingConstants.RIGHT);			// Δημιουργία JLabel και χρήση της διεπαφής SwingConstants με καθορισμό τιμής ως σταθερά το RIGHT(το οποίο έχει να κάνει με την στοίχηση του κειμένου στα δεξιά).
      lblLName.setFont(new Font("Tahoma", Font.BOLD, 14));
      lblLName.setForeground(new Color(0,102,153));
      lblLName.setPreferredSize(labelSize);				// Καθορισμός των προτινόμενων διαστάσεων.
      fieldsPanel.add(lblLName);						// Προσθήκη της ετικέτας μέσα στο fieldsPanel.
      
      txt_LName = new JTextField(10);
      txt_LName.setFont(new Font("Tahoma", Font.PLAIN, 14));
      txt_LName.setBackground(new Color(230,230,240));
      txt_LName.setPreferredSize(fieldSize);			// Καθορισμός των προτινόμενων διαστάσεων.
      txt_LName.setMaximumSize(fieldSize);
      fieldsPanel.add(txt_LName);						// Προσθήκη του txt_LName μέσα στο fieldsPanel.
      
      // Δημιουργούμε ένα panel (navigationPanel) για τα κουμπιά πλοήγησης
      JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));		// Δημιουργία JPanel ονόματι navigationPanel με FlowLayout κεντραρισμένο και με οριζόντιο gap (hgap=5 pixels μεταξύ των στοιχείων) και κατακόρυφο διάστημα(vgap=5 pixels ανάμεσα στις σειρές).
      navigationPanel.setOpaque(false);			// Το navigation panel μέσω της μεθόδου setOpaque καθίσταται διαφανές επιτρέποντας στο φόντο του γονικού container ή σε όποιο στοιχείο βρίσκεται πίσω του να φαίνεται.
      
      // Δημιουργία 4 κουμπιών για την πλοήγηση.
      JButton btnFirst = new JButton("|<");					// Μετάβαση στην πρώτη εγγραφή.
      btnFirst.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		try {			// Η χρήση του μηχανισμού try-catch block για τον χειρισμό ενδεχόμενων SQLExceptions που ενδεχομένως να προκύψουν από τις κλήσεις στο ResultSet, διότι σε περίπτωση που το rsData είναι null δεν εκτελείται το σώμα της if και ο κώδικας πηγαίνει στο catch block για τον χειρισμό του SQLException. 
                if (Main_Application.results.rsData != null) {		// Ελέγχεται εάν το rsData δεν είναι null και περιέχει δεδομένα για πλοήγηση και στην περίπτωση που δεν είναι null εκτελείται το σώμα της if. Χρησιμοποιώντας την αναφορά Main_Application.results.rsData εξασφαλίζεται πως όλες οι λειτουργίες (πλοήγηση, εμφάνιση, ενημέρωση) δουλεύουν πάνω στο ίδιο, συγκεκριμένο ResultSet που έχει ήδη φορτωθεί και έχει ανατεθεί στο παράθυρο αποτελεσμάτων καθώς αυτό σημαίνει πώς όλες οι κλάσεις που αλληλεπιδρούν με το ResultSet αναφέρονται στο ίδιο σύνολο δεδομένων.
                    Main_Application.results.rsData.first();			// Μετακίνηση του δείκτη(cursor) του ResultSet στην πρώτη εγγραφή.
                    Main_Application.results.Records();			// Καλείται η μέθοδος Records() στο παράθυρο αποτελεσμάτων, η οποία διαβάζει την τρέχουσα εγγραφή από το rsData και ανανεώνει (ενημερώνει) το GUI (TextFields) με τα δεδομένα του καθηγητή.
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
      	}
      });
      btnFirst.setBackground(new Color(200, 220, 240));
      btnFirst.setForeground(new Color(0, 102, 153));
      btnFirst.setFont(new Font("Tahoma", Font.BOLD, 17));
      
      JButton btnPrev = new JButton("<");						// Μετάβαση στην προηγούμενη εγγραφή.
      btnPrev.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		try {
                if (Main_Application.results.rsData != null && !Main_Application.results.rsData.isFirst()) {		// Ελέγχεται εάν το rsData δεν είναι null και υπάρχουν δεδομένα και ταυτόχρονα με τον λογικό τελεστή && της συνθήκης (που θα πρέπει να ισχύουν και τα 2 μαζί ταυτόχρονα για να εκτελεστεί το σώμα της if) ελέγχεται μέσω της μεθόδου isFirst() ότι ο δείκτης (cursor) του ResultSet δεν έχει ήδη φτάσει στην πρώτη εγγραφή, διότι εάν η μέθοδος αυτή επιστρέψει false σημαίνει ότι υπάρχουν προηγούμενες εγγραφές.
                    Main_Application.results.rsData.previous();			// Μετακίνηση του δείκτη στην προηγούμενη εγγραφή.
                    Main_Application.results.Records();					// Εμφάνιση δεδομένων στην οθόνη της τρέχουσας εγγραφής.
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
      	}
      });
      btnPrev.setBackground(new Color(200, 220, 240));
      btnPrev.setForeground(new Color(0, 102, 153));
      btnPrev.setFont(new Font("Tahoma", Font.BOLD, 17));
      
      JButton btnNext = new JButton(">");				// Μετάβαση στην επόμενη εγγραφή.
      btnNext.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		try {
                if (Main_Application.results.rsData != null && !Main_Application.results.rsData.isLast()) {			// Κι εδώ ελέγχεται εάν το rsData περιέχει εγγραφές και ταυτόχρονα εάν ο δείκτης δεν είναι ήδη στην τελευταία εγγραφή μέσω της μεθόδου isLast(), διότι μόνο εάν η μέθοδος επιστρέψει την τιμή true τότε επρόκειτο για την τελευταία γραμμή/σειρά ενώ σε διαφορετική περίπτωση επιστρέφει false.
                    Main_Application.results.rsData.next();			// Μετακίνηση του δείκτη στην επόμενη εγγρα΄φη του ResultSet.
                    Main_Application.results.Records();				// Ενημέρωση του GUI για την εμφάνιση των αποτελεσμάτων της τρέχουσας εγγραφής.
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
      	}
      });
      btnNext.setBackground(new Color(200, 220, 240));
      btnNext.setForeground(new Color(0, 102, 153));
      btnNext.setFont(new Font("Tahoma", Font.BOLD, 17));
      
      JButton btnLast = new JButton(">|");					// Μετάβαση στην τελευταία εγγραφή.
      btnLast.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		try {
                if (Main_Application.results.rsData != null) {		// Εδώ ελέγχεται μόνο εάν το rsData δεν είναι null και άρα περιέχει εγγραφές καθώς δεν χρειάζεται επιπλέον έλεγχος όπως πριν αφού κρίνεται απαραίτητη η μετακίνηση στην τελευταία εγγραφή.
                    Main_Application.results.rsData.last();			// Μετακίνηση του δείκτη στην τελευταία εγγραφή του ResultSet.
                    Main_Application.results.Records();				// Ενημέρωση του GUI για την παρουσίαση των δεδομένων της τελευταίας εγγραφής.
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
      	}
      });
      btnLast.setBackground(new Color(200, 220, 240));
      btnLast.setForeground(new Color(0, 102, 153));
      btnLast.setFont(new Font("Tahoma", Font.BOLD, 17));
      
      // Προσθήκη, μέσω της μεθόδου add, του κάθε κουμπιού(δηλαδή του κάθε αντικειμένου που φτιάξαμε πάνω) μέσα στο navigationPanel σύμφωνα και με τον τρόπο διάταξης που ορίζεται από το FlowLayout.
      navigationPanel.add(btnFirst);
      navigationPanel.add(btnPrev);
      navigationPanel.add(btnNext);
      navigationPanel.add(btnLast);
      
      // Δημιουργούμε ένα wrapper panel ονόματι mainPanel(που περιλαμβάνει/περιέχονται μέσα τα 2 panels, δηλαδή το 1 panel (fieldsPanel) για τα πεδία κειμένου και τα labels(=τις ετικέτες) και το 1 panel(navigationPanel) για τα κουμπιά.
      JPanel mainPanel = new JPanel();		// Το wrapper panel ονόματι mainPanel.
      mainPanel.setOpaque(false);			// Καθίσταται διαφανές.
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));		// Χρήση BoxLayout με κατακόρυφο (Y_AXIS) προσανατολισμό μέσω κλήσης της μεθόδου setLayout για το mainPanel. Αυτό σημαίνει ότι τα στοιχεία που προστίθενται θα στοιχίζονται κατακόρυφα (το ένα κάτω από το άλλο) για το mainPanel. Η πρώτη παράμετρος που περνάμε στον constructor του BoxLayout είναι το ίδιο το mainPanel. Αυτό είναι σημαντικό γιατί ο BoxLayout χρειάζεται να γνωρίζει ποιο container θα διαχειριστεί, ώστε να πάρει υπόψη του τις διαστάσεις και τα περιθώρια του. Αυτή η παράμετρος επιτρέπει στο BoxLayout να έχει πρόσβαση στις ιδιότητες του κοντέινερ, όπως το preferredSize κάθε στοιχείου, ώστε να τοποθετήσει ορθά τα επιμέρους components.
      
      Component verticalStrut = Box.createVerticalStrut(20);		// Αφού δημιουργηθεί η μεταβλητή verticalStrut(η οποία είναι τύπου Component) καλείται η μέθοδος Box.createVerticalStrut(20) δημιουργώντας ένα αόρατο component που έχει καθορισμένο ύψος 20 pixels και χρησιμέυει ως διαχωριστικός/κενός χώρος σε κάθετη διάταξη μεταξύ του mainPanel και του fieldsPanel.
      mainPanel.add(verticalStrut);				// Προσθήκη του vertical strut που δημιουργήθηκε προηγουμένως(δηλαδή, το αόρατο component με ύψος 20 pixels) για την διαμόρφωση αρχικού κενού στο mainPanel.
      mainPanel.add(fieldsPanel);				// Προσθήκη του fieldsPanel(που περιέχει όλα τα labels και τα πεδία κειμένου) μέσα στο mainPanel(σύμφωνα με το ορισμένο Layout του mainPanel που είναι το BoxLayout για κατακόρυφη στοίχηση-στον κατακόρυφο άξονα).
      mainPanel.add(Box.createVerticalStrut(20));		// Δημιουργία vertical strut με ύψος 20 pixels και προσθήκη στο mainPanel, προκειμένου να δημιουργηθεί κενό μεταξύ του fieldsPanel και του navigationPanel. Στην ουσία πρόκειται για επιπλέον κάθετο χώρο που διαχωρίζει το fieldsPanel από το επόμενο στοιχείο που θα προστεθεί στο mainPanel. Δηλαδή, δημιουργεί ένα κενό 20 pixels μεταξύ των πεδίων εισαγωγής και της περιοχής πλοήγησης.
      mainPanel.add(navigationPanel);				// Προσθήκη του navigationPanel στο mainPanel με σκοπό την τοποθέτηση κάτω από το fieldsPanel και χρήση του vertical strut (που αναλύθηκε προηγουμένως) για τον κατάλληλο οπτικό διαχωρισμό.
      
      // Τοποθέτηση του mainPanel μέσα σε ένα container (δηλαδή το centerContainer) για κεντράρισμα τόσο οριζόντια όσο και κάθετα.
      JPanel centerContainer = new JPanel(new GridBagLayout());		// Δημιουργία ενός νέου panel που χρησιμοποιεί GridBagLayout με απώτερο σκοπό την ακριβής οριζόντια και κάθετη στοίχηση στο κέντρο του mainPanel.
      centerContainer.setOpaque(false);						// Το panel γίνεται διαφανές για να έχει το ίδιο background χρώμα με το γονικό στοιχείο.
      GridBagConstraints gbc = new GridBagConstraints();		// Δημιουργεί ένα αντικείμενο GridBagConstraints, το οποίο θα καθορίσει τη θέση του mainPanel μέσα στο centerContainer.
      gbc.gridx = 0;		// Τοποθέτηση του mainPanel στην 1η στήλη.
      gbc.gridy = 0;		// Τοποθέτηση του mainPanel στην 1η σειρά/γραμμή.
      centerContainer.add(mainPanel, gbc);			// Προσθήκη του mainPanel στο centerContainer σύμφωνα με τις ρυθμίσεις που έχουν οριστεί για το gbc(δηλαδή 1η στήλη και 1η γραμμή).
      contentPane.add(centerContainer, BorderLayout.CENTER);		// Τοποθετεί/Προσθέτει το centerContainer (και έμμεσα το mainPanel με όλα τα στοιχεία του) στην κεντρική περιοχή του contentPane του παραθύρου(λόγω του BorderLayout.CENTER).

      // Footer (pnlSouth) για τα κουμπιά Update/Delete.
      JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));	// Δημιουργία JPanel για το κάτω μέρος του παραθύρου (Footer) με FlowLayout κεντραρισμένο και 5 pixels hgap και vgap, και ορίζει το φόντο του στο ίδιο χρώμα με το υπόλοιπο της διεπαφής.
      pnlSouth.setBackground(new Color(245,245,250));
      
      JButton btnUpdate = new JButton("Update");
      btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
      btnUpdate.setBackground(new Color(255,128,102));
      btnUpdate.setForeground(Color.WHITE);
      btnUpdate.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		if (txt_ID.getText().isEmpty() || txt_Name.getText().isEmpty() || txt_LName.getText().isEmpty()) {  	// Σε αυτόν τον έλεγχο if ελέγχεται εάν κάποιο από τα πεδία(ID, First name, Last name) είναι κενά, δηλαδή δεν υπάρχουν καθόλου δεδομένα μέσα συνεπώς δηλαδή ο χρήστης δεν έχει πληκτρολογήσει τίποτα σε αυτά τα πεδία με αποτέλεσμα η ενημέρωση της ισχύουσας εγγραφής να μην προχωρήσει στην περίπτωση που τα πεδία δεν έχουν καθόλου δεδομένα μέσα κι έτσι παίρνωντας true και από τα 3 μέρη της συνθήκης μέσω της μεθόδου is.Empty()-δηλαδή και τα 3 πεδία είναι κενά-τότε εκτελείται το σώμα της if ενημερώνοντας τον χρήστη μέσω ενός πλαισίου διαλόγου ότι δεν είναι εφικτή η ενημέρωση της εγγραφής με κενά πεδία.
      	            JOptionPane.showMessageDialog(contentPane, "You cannot proceed to update the record with empty fields (ID, Name, Last Name)!", "Missing Data", JOptionPane.ERROR_MESSAGE);
      	            return;		// Μέσω της εντολής return τερματίζεται η εκτέλεση της μεθόδου (actionPerformed) και του υπόλοιπου κώδικα που ακολουθεί λόγω έλλειψης δεδομένων κι έτσι αποφεύγεται τυχόν προσπάθεια ενημέρωσης δεδομένων με ελλιπή στοιχεία.
      	       }
           
      		try {
      			Connection myconn = Application.conn;	 // Σε αυτό το σημείο μπορούμε να δούμε το αντικείμενο της σύνδεσης, όπου πατώντας ο χρήστης το κουμπί update θα πραγματοποιείται η τροποποίηση/η ενημέρωση μίας εγγραφή στην βάση δεδομένων. Εκχωρώ το αντικείμενο που βρίσκεται μέσα στο πεδίο αποθηκευμένο της κλάσης Application σε μία τοπική μεταβλητή myconn τύπου Connection. Αυτή η τοπική μεταβλητή myconn που είναι τύπου Connection θα χρησιμεύσει για την αποστολή sql ερωτημάτων στην βάση δεδομένων.
      			int id = Integer.parseInt(txt_ID.getText());		// Το περιεχόμενο που μπαίνει μέσα στο JTextField μέσω της μεθόδου getText() του πεδίου txt_ID μετατρέπεται σε ακέραιο αριθμό μέσω του Integer.parseInt κι εκχωρείται σε μία μεταβλητή id τύπου int, προκειμένου να συμφωνεί με τον τύπο δεδομένου που έχει δηλωθεί για το id στην βάση δεδομένων. Η τιμή που θα σταλεί στην βάση δεδομένων θα πρέπει να είναι συμβατή με την τιμή που έχει οριστεί για το id (τύπου int δηλαδή).
      			String sql = "UPDATE teachers SET firstname = ?, lastname = ? " + "WHERE id = ? AND (firstname <> ? OR lastname <> ?)";		// Εντός της μεταβλητής sql εκχωρείται το ερώτημα update sql query, μέσω του οποίου δίνεται η δυνατότητα τροποποίησης των τιμών που περιέχονται στις γραμμές των στηλών του πίνακα teachers καθώς μέσω του SET στα πεδία firstname και lastname τα ? θα αντικατασταθούν και θα ενημερωθούν σύμφωνα με τις τιμές που θα δοθούν από τον χρήστη της εφαρμογής ενώ μέσω του WHERE = id καθορίζεται πώς μόνο η συγκεκριμένη εγγραφή με το συγκεκριμένο id θα ενημερωθεί και ταυτόχρονα μέσω της συνθήκης firstname <> ? OR lastname <> ? ελέγχεται εάν έχει υπάρξει κάποια αλλαγή είτε στο firstname είτε στο lastname καθώς με αυτόν τον τρόπο διασφαλίζεται ότι η ενημέρωση σε μία εγγραφή θα πραγματοποιηθεί μόνο στην περίπτωση που έχει υπάρξει κάποια αλλαγή στις τιμές που εισάγει ο χρήστης είτε δηλαδή στο firstname είτε στο lastname ειδάλως εάν δεν έχει υπάρξει κάποια αλλαγή στις προαναφερθέισες τιμές τότε ενημερώνεται ο χρήστης μέσω ενός πλαισίου διαλόγου ότι δεν έγιναν αλλαγές στην ισχύουσα εγγραφή.  
                PreparedStatement ps = myconn.prepareStatement(sql);		// Δημιουργεί ένα αντικείμενο PreparedStatement με βάση το SQL ερώτημα που ορίστηκε στη μεταβλητή sql.
                ps.setString(1, txt_Name.getText());			// Καλείται η μέθοδος setString στο PreparedStatement ps για να αντικαταστήσει το πρώτο placeholder ? στο SQL ερώτημα με τη συμβολοσειρά που ανακτάται από το TextField txt_Name (που περιέχει το πρώτο όνομα). Ορίζει την τιμή του πεδίου firstname ώστε να ενημερωθεί με την τιμή που έχει εισαχθεί στο αντίστοιχο πεδίο του GUI.
                ps.setString(2, txt_LName.getText());
                ps.setInt(3, id);			// Μέσω της μεθόδου setInt, ο οδηγός (JDBC Driver) μετατρέπει την ληφθείσα τιμή σε τύπο δεδομένου SQL_Integer στην περίπτωση που κρίνεται αναγκαίο να σταλεί αυτή η τιμή στην βάση δεδομένων. Ορίζει την τιμή στο πεδίο id για την επιλογή της εγγραφής που θέλουμε να ενημερώσουμε (δηλαδή, ενημερώνει την εγγραφή όπου το id ισούται με αυτήν την τιμή).
                ps.setString(4, txt_Name.getText());  	// Σε αυτό το σημείο πραγματοποιείται αντιστοίχηση του 4ου ? (firstname <> ?) με την τιμή που θα δώσει ο χρήστης στο πεδίο του ονόματος (txt_Name), αφού σύμφωνα και με την συνθήκη του WHERE που έχει οριστεί ελέγχεται μέσω της σύγκρισης αυτής εάν υπάρχει διαφορά μεταξύ της τρέχουσας τιμής στο πεδίο txt_Name και της καινούργιας τιμής που αφορά το firstname.
                ps.setString(5, txt_LName.getText());	// Αντιστοίχηση του 5ου ? (OR lastname <> ?) με την τιμή του πεδίου txt_LName προκειμένου να πραγματοποιηθεί σύγκριση στην συνθήκη του WHERE για το lastname με την τρέχουσα τιμή του πεδίου txt_LName.
                int updated = ps.executeUpdate();		// Αποστολή του sql ερωτήματος ενημέρωσης(update) στον server και εκτέλεση από τον server καθώς το αποτέλεσμα εκχωρείται σε μία μεταβλητή ακέραιου τύπου επιστρέφοντας έναν ακέραιο που καθορίζει πόσες εγγραφές έχουν γίνει update από το ερώτημα ενώ εάν είναι μεγαλύτερος ο αριθμός από το 0, σηματοδοτεί την επιτυχή ενημέρωση μίας εγγραφής.
                ps.close();			// Κλείσιμο του αντικειμένου PreparedStatement για την απελευθέρωση πόρων που χρησιμοποιήθηκαν.
                if (updated == 0) { 	// Σε αυτό το σημείο ελέγχεται μέσω του if η τιμή της μεταβλητής updated, όπου στην προκειμένη περίπτωση ισοδυναμεί με το 0 που σημαίνει ότι δεν πραγματοποιήθηκε καμία ενημέρωση σε καμία εγγραφή και αυτό συμβαίνει λόγω του γεγονότος ότι είτε δεν υπάρχει εγγραφή καταχωρημένη με το συγκεκριμένο id είτε δεν έχει υπάρξει αλλαγή στις τιμές(firstname, lastname) μίας εγγραφής, δηλαδή οι νέες τιμές που εισήγαγε ο χρήστης είναι ίδιες με τις υπάρχουσες τιμές κι άρα δεν πρόκειται για κάποια αλλαγή/τροποποίηση στην ισχύουσα εγγραφή αφού το firstname και το lastname έχουν παραμείνει ίδια ακόμα κι αν ο χρήστης δεν προχωρήσει σε επεξεργασία των πεδίων ή πληκτρολογήσει ξανά τις ίδιες τιμές. Έτσι παίρνοντας true από τον έλεγχο αυτής της συνθήκης εκτελείται το σώμα της if (αφού ο αριθμός των ενημερωμένων εγγραφών ισοδυναμεί με το 0-που σημαίνει ότι δεν έχει υπάρξει καμία ενημέρωση σε καμία εγγραφή-) κι έτσι εμφανίζεται το παράθυρο διαλόγου ενημερώνοντας τον χρήστη ότι δεν υπήρξε καμία αλλαγή στην ισχύουσα εγγραφή.
                	JOptionPane.showMessageDialog(Main_Application.results, "There have been no changes to this registration!", "Unsuccessfull Update", JOptionPane.WARNING_MESSAGE);
               } else {				// Το σώμα της else θα εκτελεστεί μόνο εάν η μεταβλητή updated είναι γνησίως μεγαλύτερη του 0 (updated >0), που σημαίνει ότι υπήρξε πράγματι αλλαγή στις τιμές και επιστράφηκαν ενημερωμένες εγγραφές εμφανίζοντας στον χρήστη το αντίστοιχο παράθυρο διαλόγου για την επιτυχή ενημέρωση μίας εγγραφής.
                    JOptionPane.showMessageDialog(Main_Application.results, "The selected registration was updated successfully!", " Successfull Update", JOptionPane.INFORMATION_MESSAGE);
                    updatedRecords();			// Καλείται η μέθοδος updatedRecords() για να επαναφορτωθούν τα νέα δεδομένα από την βάση για την ενημέρωση του αντικειμένου rsData τύπου ResultSet με άμεση απόρροια την αναπαράσταση αυτών των αλλαγών.
                    shownextResult();			// Καλείται η μέθοδος shownextResult() για την εμφάνιση της επόμενης εγγραφής στο GUI καθώς μέσα σε αυτές τις εγγραφές θα εμφανιστεί και η ενημερωμένη πλέον εγγραφή (δηλαδή με το αλλαγμένο όνομα ή επώνυμο). 
               }
            } catch (SQLException ex) {
            	JOptionPane.showMessageDialog(contentPane, "Connection to Database failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
      	}
      });  
      
      JButton btnDelete = new JButton("Delete");
      btnDelete.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		if (txt_ID.getText().isEmpty() || txt_Name.getText().isEmpty() || txt_LName.getText().isEmpty()) {		// Έλεγχος εάν τα πεδία είναι κενά και στην περίπτωση που είναι κενά ενημερώνεται ο χρήστης για το μήνυμα σφάλματος, διότι δεν μπορεί να προχωρήσει σε αυτή την ενέργεια με κενά πεδία.
  	            JOptionPane.showMessageDialog(contentPane, "You cannot delete a record with empty fields (ID, First Name, Last Name)!", "Missing Data", JOptionPane.ERROR_MESSAGE);
  	            return;		// Τερματισμός ενέργειας με το return.
  	       }
      		
      	int delete = JOptionPane.showConfirmDialog(Main_Application.results, "Delete registration?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);	// Δημιουργία ενός πλαισίου διαλόγου επιβεβαίωσης πριν την οριστική διαγραφή μίας εγγραφής αναμένοντας μία προκαθορισμένη απάντηση κι επιστρέφοντας μία ακέραια τιμή μέσω χρήσης της μεθόδου showConfirmDialog με καθορισμό των επιλογών που θα έχει ο χρήστης(YES-NO) ενώ επιστρέφει έναν ακέραιο αριθμό αναλόγως την επιλογή(πχ. YES-OPTION) που έχει κάνει ο χρήστης της εφαρμογής καθώς εκχωρείται μέσα σε μία μεταβλητή ακέραιου τύπου.
      	if (delete == JOptionPane.YES_OPTION) {		// Εδώ ελέγχεται εάν ο χρήστης της εφαρμογής συμφώνησε με την επιλογή(YES-OPTION), δηλαδή να προχωρήσει στην διαγραφή μίας εγγραφής κι εκτελείται το κομμάτι κώδικα που υπάρχει μέσα στο try-block. Εάν η τιμή της μεταβλητής delete δεν ισοδυναμεί με το YES-OPTION κι άρα ο χρήστης έχει επιλέξει είτε το NO ειτε το Cancel, τότε δεν εκτελείται ο κώδικας που υπάρχει μέσα στο try-block καθώς δεν γίνεται καμία ενέργεια και παραμένει στο ίδιο το παράθυρο των αποτελεσμάτων.
      		try {		// Χρήση μηχανισμού για την διαχείρηση ενδεχόμενου SQLException που ίσως προκύψει λόγω εκτέλεσης του sql ερωτήματος delete.
      		Connection myconn = Application.conn;			// Σε αυτό το σημείο μπορούμε να δούμε το αντικείμενο της σύνδεσης, όπου πατώντας ο χρήστης το κουμπί delete θα διαγράφει μία εγγραφή από την βάση δεδομένων. Εκχωρώ το αντικείμενο που βρίσκεται μέσα στο πεδίο αποθηκευμένο της κλάσης Application σε μία τοπική μεταβλητή myconn τύπου Connection. Αυτή η τοπική μεταβλητή myconn που είναι τύπου Connection θα χρησιμεύσει για την αποστολή sql ερωτημάτων στην βάση δεδομένων.
      		String del = "DELETE FROM teachers WHERE id = ?";			// Εντός της μεταβλητής del εκχωρείται το delete sql query όπου το ? αντικαθίσταται από την τιμή id που επρόκειτο να διαγραφεί. Μέσω της εντολής DELETE δίνεται η δυνατότητα διαγραφής γραμμών από τον πίνακα teachers που έχουμε φτιάξει στην βάση δεδομένων καθώς μέσω του όρου WHERE εισάγεται μία συνθήκη που αφορά το id της εκάστοτε εγγραφής που επιθυμούμαι να διαγράψουμε. Χωρίς την ύπαρξη του όρου WHERE διαγράφονται όλες οι γραμμές.
      		PreparedStatement ps = myconn.prepareStatement(del);		// Δημιουργία του PreparedStatement μέσω χρήσης του sql ερωτήματος delete που ορίσαμε βάζοντας ως παράμετρο της μεθόδου την μεταβλητή del που έχει εκχωρημένο το sql query και αντικατάσταση του ? για το id.
      		int id = Integer.parseInt(txt_ID.getText());		// Το περιεχόμενο που μπαίνει μέσα στο JTextField μέσω της μεθόδου getText() του πεδίου txt_ID μετατρέπεται σε ακέραιο αριθμό μέσω του Integer.parseInt κι εκχωρείται σε μία μεταβλητή id τύπου int, προκειμένου να συμφωνεί με τον τύπο δεδομένου που έχει δηλωθεί για το id στην βάση δεδομένων.
            ps.setInt(1, id);			// Αντικατάσταση του ? με την τιμή id που τίθεται μέσω χρήσης της μεθόδου setInt του PreparedStatemnent. Μέσω της μεθόδου setInt, ο οδηγός (JDBC Driver) μετατρέπει την ληφθείσα τιμή σε τύπο δεδομένου SQL_Integer στην περίπτωση που κρίνεται αναγκαίο να σταλεί αυτή η τιμή στην βάση δεδομένων.
      		int confirmdel = ps.executeUpdate();		// Αποστολή του sql ερωτήματος διαγραφής στον server και εκτέλεση από τον server καθώς το αποτέλεσμα εκχωρείται σε μία μεταβλητή ακέραιου τύπου επιστρέφοντας έναν ακέραιο που καθορίζει πόσες εγγραφές διαγράφηκαν από το ερώτημα ενώ εάν είναι μεγαλύτερος ο αριθμός από το 0, σηματοδοτεί την επιτυχή διαγραφή μάις εγγραφής.
      		if (confirmdel > 0) {						// Εδώ ελέγχεται εάν η τιμή της μεταβλητής είναι μεγαλύτερη από το 0, τότε επιτυχώς διαγράφηκε μία εγγραφή και εκτελείται το σώμα της if εμφανίζοντας ένα pop-up παράθυρο στον χρήστη για το ότι η συγκεκριμένη εγγραφή διαγράφηκε επιτυχώς ενώ σε διαφορειτκή περίπτωση εάν η τιμή δεν είναι μεγαλύτερη του 0(άρα είναι ίση με το 0), τότε αυτό σημαίνει ότι δεν πραγματοποιήθηκε καμία εγγραφή(ίσως λόγω μή ύπαρξης εγγραφής).
      			JOptionPane.showMessageDialog(Main_Application.results, "The selected registration was deleted successfully!", "Delete", JOptionPane.INFORMATION_MESSAGE);		// Ενημέρωση του χρήστη για επιτυχή διαγραφή.
      			rsData.deleteRow();			// Μέσω της μεθόδου deleteRow(), διαγράφεται η τρέχουσα γραμμή από το αντικείμενο rsData που είναι τύπου ResultSet και κατ'επέκταση πραγματοποιείται διαγραφή και από την βάση δεδομένων.
      			updatedRecords();		// Μετά την διαγραφή της τρέχουσας γραμμής που προηγήθηκε με την χρήση της μεθόδου deleteRow, καλείται η μέθοδος updatedRecords() μέσω της οποίας πραγματοποιείται επαναφόρτιση των δεδομένων του ResultSet από την βάση για την απεικόνιση της τρέχουσας κατάστασης μετά την διαγραφή μίας εγγραφής.
      			shownextResult();		// Καλείται η μέθοδος shownextResult() για ενημέρωση του γραφικού περιβάλλοντος(που λογικά η εγγραφή που μόλις διαγράφηκε δεν θα υπάρχει στο γραφικό περιβάλλον επικοινωνίας του χρήστη) αφού ο δείκτης(cursor) μετακινείται στην επόμενη εγγραφή εάν υπάρχει. 
      		} 
      		ps.close();		// Κλείσιμο του PreparedStatement.
      		clearForm(); 		// Κλήση της μεθόδου clearForm() για εκκαθάριση των πεδίων αφού πρώτα ο χρήστης ενημερωθεί για την επιτυχή διαγραφή της εγγραφής.
      		} catch (SQLException ex) {
        	JOptionPane.showMessageDialog(contentPane, "Connection to Database failed.", "Error", JOptionPane.ERROR_MESSAGE);
        	}
      	  }
      	}
      });
      
      btnDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
      btnDelete.setBackground(new Color(255,128,102));
      btnDelete.setForeground(Color.WHITE);
      
      pnlSouth.add(btnUpdate);		 // Προσθήκη του κουμπιού Update και του κουμπιού Delete στο pnlSouth(στο κάτω το πάνελ) με διάταξη σύμφωνη με το FlowLayout(που έχει καθοριστεί για αυτό το panel).
      pnlSouth.add(btnDelete);
      contentPane.add(pnlSouth, BorderLayout.SOUTH);	 // Τοποθέτηση/Προσθήκη του pnlSouth(και ότι περιέχει μέσα αυτό το πάνελ) μέσα στην περιοχή του contentPane σύμφωνα και με τον διαχειριστή διάταξης που ορίζεται από το contentPane που είναι με χρήση της σταθεράς SOUTH(νότιο τμήμα) για το bottom του container.
      
    }	// Εδώ κλείνει ο κατασκευαστής.
    
    public void setResultSet(ResultSet rs) {		// Μέσω αυτής της μεθόδου διασφαλίζεται ότι το αντικείμενο των αποτελεσμάτων - έχει πρόσβαση στο ResultSet που περιέχει τα δεδομένα από το query, έτσι ώστε τα κουμπιά πλοήγησης να δουλεύουν επάνω σε αυτό το sql ερώτημα.
        this.rsData = rs;							// Ανάθεση της τοπικής μεταβλητής rs(παραμέτρου της μεθόδου) εντός του τρέχοντος πεδίου rsData του παραθύρου των αποτελεσμάτων.
    }

    public void Records() {			// Μέθοδος εμφάνισης των δεδομένων από το τρέχον ResultSet κι ενημέρωση του γραφικού περιβάλλοντος διεπαφής του GUI.
        try {					// Χρήση του try block, διότι η χρήση μεθόδων getInt & getString ενδεχομένως να προκαλέσουν κάποιο SQLException σε περίπτωση που το ResultSet έχει κλείσει ή δεν περιέχει το ζητούμενο πεδίο διασφαλίζοντας την σωστή εκτέλεση του προγράμματος κι ενημερώνοντας στο console τον προγραμματιστή πιο συγκεκριμένα για το σφάλμα που προκλήθηκε.
            if (rsData != null) {		// Σε αυτό το σημείο ελέγχεται εάν η καθολική μεταβλητή/το πεδίο που δηλώθηκε πάνω και αναπαριστά το ResultSet, περιέχει δεδομένα του query(ερωτήματος) και δεν είναι null διασφαλίζοντας ότι περιέχει ένα έγκυρο ResultSet πριν υλοποιηθεί η ανάγνωση των δεδομένων. 
                int teacherID = rsData.getInt("id");		// Αναζήτηση του πεδίου με όνομα "id" στον πίνακα και επιστροφή της τιμής αυτού ως τύπου int. Από το τρέχον record του rsData(στο οποίο δείχνει ο δείκτης του ResultSet) διαβάζεται η τιμή του πεδίου id καθώς επιστρέφεται και ως τύπος δεδομένου int (όπως έχει δηλωθεί και στην βάση δεδομένων).
                String teacherFirstName = rsData.getString("firstname");	// Σε αυτό το σημείο ελέγχεται εάν το πεδίο "firstname" υπάρχει στον πίνακα της βάσης δεδομένων(όπως ακριβώς δηλαδή έχει δηλωθεί) και ότι ο τύπος επιστροφής των δεδομένων είναι ο σωστός.
                String teacherLastName = rsData.getString("lastname");

                displayTeacher(teacherID, teacherFirstName, teacherLastName);	// Σε αυτό το σημείο καλείται η μέθοδος αυτή με παραμέτρους εισόδου τις μεταβλητές που ορίστηκαν παραπάνω και αφορούν τα στοιχεία της τρέχουσας εγγραφής από την βάση δεδομένων, καθώς αυτά τα στοιχεία θέλουμε να βλέπει ο χρήστης της εφαρμογής στο GUI μέσω ενημέρωσης του ίδιου του περιβάλλοντος διεπαφής.
            }
        } catch (SQLException ex) {				// Αν για κάποιο λόγο προκύψει πρόβλημα στην ανάγνωση δεδομένων (π.χ. το πεδίο δεν υπάρχει, το ResultSet έχει κλείσει, κλπ.), τότε η εξαίρεση θα πιαστεί εδώ και θα εκτελεστεί το σώμα του catch block με την εμφάνιση του συγκεκριμένου pop-up παραθύρου.
        	JOptionPane.showMessageDialog(contentPane, "No more registrations to display!", "Error", JOptionPane.ERROR_MESSAGE);		// Ενημέρωση του χρήστη ότι κάτι πήγε στραβά κατά την προσπάθεια εμφάνισης της συγκεκριμένης εγγραφής.
        }
    }

    public void displayTeacher(int teacherID, String teacherFName, String teacherLName) {		// Εμφάνιση των αποτελεσμάτων δηλαδή των δεδομένων του καθηγητή στην φόρμα των αποτελεσμάτων στα αντίστοιχα TextFields.
    	txt_ID.setText(String.valueOf(teacherID));												// Λόγω του γεγονότος ότι η μέθοδος setText δέχεται ως παράμετρο εισόδου μόνο τύπο δεδομένου String, για το teacherID το οποίο είναι δηλωμένο στην βάση δεδομένων ως τύπος δεδομένου int, γίνεται χρήση της μεθόδου String.valueOf όπου λαμβάνοντας ως παράμετρο το teacherID που είναι τύπος δεδομένου int μετατρέπεται σε τύπο δεδομένου String.
        txt_Name.setText(teacherFName);
        txt_LName.setText(teacherLName);
    }
    
    private void clearForm() {		// Δημιουργία μεθόδου clearForm() για εκκαθάριση των πεδίων αφού πρώτα ο χρήστης πατήσει το κουμπί Update(για να κάνει τις απαραίτητες τροποποιήσεις σε μία εγγραφή) και στην περίπτωση που επιθυμεί μία διαγραφή πραγματοποιείται εκκαθάριση των πεδίων από την προηγούμενη εγγραφή.
        txt_ID.setText("");
        txt_Name.setText("");
        txt_LName.setText("");
    }
    
    private void shownextResult() {			// Μέσω της μεθόδου αυτής θα εμφανίζεται η επόμενη εγγραφή στο γραφικό περιβάλλον διεπαφής του χρήστη.
    	try {						// Χρήση μηχανισμού try-catch για τον χειρισμό sql exceptions στην περίπτωση αδυναμίας πρόσβασης στο ResultSet.
    		boolean moreResults = rsData.next();	// Μέσω της μεθόδου next που εφαρμόζεται πάνω στο αντικείμενο του ResultSet, δηλαδή το rsData, μετακίνηση του δείκτη(cursor) στην επόμενη εγγραφή κι εκχώρηση σε μία boolean μεταβλητή αφού και το αποτέλεσμα επιστροφής της μεθόδου είναι μία boolean τιμή, επιστρέφοντας true στην περίπτωση ύπαρξης επόμενης εγγραφής ειδάλως επιστρέφεται false αν ο δείκτης έφτασε στο τέλος και δεν έχει βρει άλλη εγγραφή. 
    		if(!moreResults) {			// Μέσω μίας δομής διακλάδωσης if, ελέγχεται εάν δεν υπάρχουν άλλες εγγραφές εντός της boolean μεταβλητής moreResults, όπου παίρνοντας true από την συνθήκη εκτελείται το σώμα της if και πραγματοποιείται μετακίνηση του δείκτη στην πρώτη εγγραφή του αντικειμένου ResultSet με αποτέλεσμα να ξεκινάει η πλοήγηση ξανά από την αρχή κι έτσι πραγματοποιείται ενημέρωαη του GUI με τα δεδομένα που αφορούν την πρώτη εγγραφή καλώντας την μέθοδο Records().  
    			rsData.first();
    			Records();
    		}
    	} catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    }
    
    private void updatedRecords() {		// Μέσω αυτής της μεθόδου επαναφορτώνονται τα δεδομένα από την βάση δεδομένων καθώς το αντικέιμενο ResultSet(rsData) ενημερώνεται μετά την εκτέλεση κάποιας τροποποίησης είτε αυτό αφορά το update είτε to delete. 
        try {						// Ο κώδικας πρέπει να περικλείεται από τον μηχανισμό try-catch στην περίπτωση που προκληθούν sql exceptions.
            if (rsData != null) {		// Σε αυτό το σημείο μέσω της if, πρώτα ελέγχεται εάν το προηγούμενο rsData δεν είναι null (που αντικατόπτριζε την προηγούμενη εγγραφή πριν δηλαδή την τροποποίσή της και πιο συγκεκριμένα πριν να υποστεί κάποιο update ή κάποιο delete) κι έτσι στην περίπτωση που υπάρχουν εγγραφές μέσω χρήσης της μεθόδου close() στο αντικείμενο rsData κλείνεται το προηγούμενο ResultSet προκειμένου να δημιουργηθεί το νέο ResultSet. 
                rsData.close();		// κλείσιμο του αντικειμένου rsData.
            }
            Statement stquery = Application.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);	 // Κι αφού προηγήθηκε ο έλεγχος στον από πάνω κώδικα, σε αυτό το σημείο, δημιουργείται μέσω χρήσης της μεθόδου createStatement() ένα νέο Statement αντικείμενο της κλάσης Statement, το οποίο θα χρησιμοποιηθεί για το sql ερώτημα στην βάση δεδομένων μέσω χρήσης της σύνδεσης Application.conn καθορίζοντας το ResultSet ως TYPE_SCROLL_SENSITIVE, κάτι το οποίο σημαίνει ότι οποιεσδήποτε αλλαγές πραγματοποιηθούν στις εγγραφές (είτε ενημέρωσης εγγραφής είτε διαγραφής αυτής) επρόκειτο να αντικατοπτρίζονται στην βάση δεδομένων ενώ ταυτόχρονα τίθενται ως CONCUR_UPDATABLE προκειμένου να πραγματοποιηθεί ενημέρωση ή διαγραφή γραμμών μέσω του ResultSet.    
            rsData = stquery.executeQuery("SELECT * FROM teachers");		// Εκτέλεση του sql query (δηλαδή θα εμφανίζει όλες τις εγγραφές από τον πίνακα teachers) πάνω στο τρέχον αντικείμενο που είναι το stquery της κλάσης Statement κι ανάθεση του αποτελέσματος στην μεταβλητή rsData.
            if (rsData.next()) { 	 // Σε αυτό το σημείο ελέγχεται μέσω της μεθόδου next(), εάν το αντικείμενο rsData περιέχει επόμενη εγγραφή και στην περίπτωση που η συνθήκη δώσει αποτέλεσμα true, τότε εκτελείται το σώμα της if και μετακινείται ο δείκτης(cursor) στην πρώτη εγγραφή μέσω κλήσης της μεθόδου rsData.first().
                rsData.first();		// Μετακίνηση στην πρώτη εγγραφή.
                Records();			// Καλείται η μέθοος Records() για την εμφάνιση της πρώτης επικαιροποιημένης εγγραφής.
            } else {				// Στην περίπτωση που η συνθήκη της if επιστρέψει αποτέλεσμα false, δηλαδή δεν υπάρχει επόμενη εγγραφή μέσα στο αντικείμενο rsData, τότε εκτελείται το σώμα της else όπου καλείται η μέθοδος clearForm(), η οποία καθαρίζει τα πεδία(JTextFields) από το GUI με απώτερο σκοπό την μή εμφάνιση των προηγούμενων δεδομένων ενώ σε περίπτωση που έπειτα από ενέργεια που θα έχει κάνει ο ίδιος ο χρήστης και φεριποίην θα έχει διαγράψει μέσω του παραθύρου GUI όλες τις εγγραφές από την βάση, τότε ενημερώνεται μέσω ενός pop-up παραθύρου για την μή ύπαρξη άλλων εγγραφών. 
                clearForm();
                JOptionPane.showMessageDialog(contentPane, "There are no records available!", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {		// Χειρισμός του SQLException λόγω προβλήματος κατά την εκτέλεση του sql query ή επεξεργασίας του ResultSet. 
            ex.printStackTrace();
        }
    }

}	   // Εδώ κλείνει όλη η κλάση.
