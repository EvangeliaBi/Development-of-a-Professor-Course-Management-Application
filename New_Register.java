package exetastiki_earino;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class New_Register extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textName;
    private JTextField textLName;

     // Εκτέλεση παραθύρου από την μέθοδο main.
   // public static void main(String[] args) {
    //    EventQueue.invokeLater(new Runnable() {
        //	public void run() {
         //   try {
           //     New_Register frame = new New_Register();
             //   frame.setMinimumSize(new Dimension(450, 300));		// Για το τρέχον αντικείμενο frame της υποκλάσης New_Register καλείται η μέθοδος setMinimumSize, όπου μέσω αυτής υπερκαλύπτεται η μέθοδος setMinimumSize της κλάσης Component(από το πακέτο java.awt) καθορίζοντας το ελάχιστο μέγεθος του παραθύρου εντός μιας σταθερής τιμής καθώς σε περίπτωση που ο χρήστης αποπειραθεί να αλλάξει το μέγεθος του παραθύρου τότε το σύστημα θα αποφυγεί αυτή την μετατροπή κάτω από τις διαστάσεις που ορίστηκαν μέσω παραμέτρων που τέθηκαν στην δημιουργία του καινούργιου αντικειμένου καλώντας τον κατασκευαστή Dimension(δηλαδή το παράθυρο δεν μπορεί να μικρύνει κάτω από 450 pixels πλάτος και κάτω από 300 pixels ύψος), διασφαλίζοντας την πλήρη εμφάνιση όλων των components(ετικέτες, πεδία κειμένου και κουμπιά).
             //   frame.setLocationRelativeTo(null);	   // Εντός της μεθόδου main κι αφού έχει δημιουργηθεί το αντίστοιχο αντικείμενο(frame) της κλάσης του πλαισίου JFrame καλείται η μέθοδος setLocationRelativeTo() με όρισμα της μεθόδου το null, διασφαλίζοντας ότι το πλαίσιο θα κεντραριστεί στην οθόνη.
             //   frame.setVisible(true);				// Το παράθυρο(φόρμα εγγραφής) γίνεται ορατό.
          //  } catch (Exception e) {
             //   e.printStackTrace();
         //   }
        //  }
      //  });
   // }

     // Δημιουργία παραθύρου.
    public New_Register() {										// Δημιουργία του κατασκευαστή.
        setBackground(new Color(240, 248, 255));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {			// Όταν ο χρήστης κλείσει το τρέχον παράθυρο εκτελείται το σώμα της μεθόδου αυτής.
                Main_Application.insert.setEnabled(true);		// Όταν ο χρήστης πατήσει το κουμπί X από το τρέχον παράθυρο(της φόρμας εγγραφής) ενεργοποιείται ξανά το προηγούμενο παράθυρο(το Window_Search_Insert) μέσω κλήσης του public static πεδίου insert από την κύρια κλάση(Main_Application που τρέχει όλη η εφαρμογή).
            }
        });
        setTitle("New Registration");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);			// Καθορίζει ότι όταν ο χρήστης κλείνει το παράθυρο, αυτό δεν θα καταστραφεί αλλά απλώς θα κρύβεται (δεν τερματίζεται η εφαρμογή).
        setBounds(100, 100, 500, 350);							// Καθορισμός συντεταγμένων(x,y) και πλάτους και ύψους του παραθύρου.

        // Κύριο πάνελ με BorderLayout(για το contentPane).
        contentPane = new JPanel();								// Δημιουργία panel που θα λειτουργεί ως κύριο container.
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));			// Προσθέτει ένα κενό περιθώριο (margin) 5 pixels από όλες τις πλευρές.
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);							// Καθορίζει το contentPane ως το βασικό κοντέινερ του παράθυρου.

        // Header panel στο NORTH με τον τίτλο "Registration Form".
        JPanel pnlNorth = new JPanel();							// Δημιουργία του πάνελ(pnlNorth) με διαχειριστή διάταξης FlowLayout(Δες το IntroApplicationPlatform το pnlNorth για να συμπληρώσουμε από εκεί).
        pnlNorth.setBackground(new Color(255, 229, 204));
        JLabel lblNewRegister = new JLabel("Registration Form");
        lblNewRegister.setForeground(new Color(0, 51, 102));
        lblNewRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pnlNorth.add(lblNewRegister);							// Προσθήκη της ετικέτας(lblRegister) εντός του pnlNorth(δηλαδή μέσα στο βόρειο πάνελ).
        contentPane.add(pnlNorth, BorderLayout.NORTH);			// Τοποθέτηση του panel στην περιοχή North του contentPane σύμφωνα και με το BorderLayout.

        // Δημιουργούμε το pnlCenter που περιέχει τη φόρμα με GridBagLayout.
        JPanel pnlCenter = new JPanel(new GridBagLayout());			// Δημιουργία του pnlCenter και χρήση του GridBagLayout.
        pnlCenter.setBackground(new Color(255, 229, 204));
        
        // Δημιουργούμε μία "βάση" για τις ρυθμίσεις των constraints.
        GridBagConstraints baseGbc = new GridBagConstraints();		// Δημιουργία αντικειμένου GridBagConstraints(baseGbc) για τα components.
        baseGbc.insets = new Insets(10, 10, 10, 10);				// Για το αντικέιμενο (baseGbc) δημιουργείται απόσταση(10 pixels padding) γύρω από κάθε στοιχείο(δηλαδή σε όλες τις πλευρές).
        baseGbc.weightx = 1.0;										// Καθορίζεται του πώς θα κατανεμηθεί ο διαθέσιμος οριζόντιος χώρος(μεταξύ στηλών και γραμμών).
        baseGbc.anchor = GridBagConstraints.CENTER;					// Ορίζει την προεπιλεγμένη θέση (center) των στοιχείων εντός κάθε κελιού, όπου στην προκειμένη περίπτωση που η παράμετρος anchor εφαρμόζεται πάνω στο αντικείμενο, διασφαλίζεται ότι όταν το συστατικό είναι μικρότερο από τον χώρο που το περιέχει μέσω αυτής της παραμέτρου καθορίζεται πού θα τοποθετηθεί και στην προκειμένη περίπτωση με τιμή GridBagConstraints.CENTER ορίζεται ότι το component θα τοποθετηθεί στο κέντρο του κελιού.

        GridBagConstraints gbcTop = (GridBagConstraints) baseGbc.clone();			// Κλωνοποιείται το βασικό GridBagConstraints για το filler στο πάνω μέρος
        gbcTop.gridx = 0;											// Το στοιχείο ξεκινά από την στήλη 0, δηλαδή 1η(γιατί ξεκινάμε να μετράμε από το 0).
        gbcTop.gridy = 0;											// Το στοιχείο ξεκινά από την γραμμή 0, δηλαδή 1η.
        gbcTop.gridwidth = 3;										// Θα καλύπτει 3 στήλες.
        gbcTop.weighty = 1.0;  										// Απορροφά το επιπλέον κάθετο χώρο πάνω, δηλαδή θα πάρει όλο τον κάθετο επιπλέον χώρο (μέσω του weighty και της ρύθμισης fill).
        gbcTop.fill = GridBagConstraints.VERTICAL;					// Σε αυτό το σημείο το αντικέιμενο μέσω χρήσης του GridBagConstraints.VERTICAL, έχει ως σκοπό να καταλάβει όλο το διατιθέμενο ύψος χωρίς την μεταβολή του μήκους του σε κάθετη κατέυθυνση εντός του κελιού που έχει οριστεί.
        pnlCenter.add(Box.createVerticalGlue(), gbcTop);			// Δημιουργεί έναν "αόρατο" χώρο που επιτρέπει στο layout να γεμίσει το διαθέσιμο κάθετο χώρο, διατηρώντας έτσι την ομοιόμορφη κατανομή.

        // ID Label & TextField
        GridBagConstraints gbcIDLabel = (GridBagConstraints) baseGbc.clone();		// Κλωνοποίηση του στοιχείου baseGbc για τον καθορισμό συγκεκριμένων ρυθμίσεων για την ετικέτα (gbcIDLabel).
        gbcIDLabel.gridx = 0;										// Τοποθέτηση στην 1η στήλη.
        gbcIDLabel.gridy = 1;										// Τοποθέτηση στην 2η σειρά.
        gbcIDLabel.anchor = GridBagConstraints.EAST;				// Δεξιά ευθυγράμμιση.
        JLabel lblID = new JLabel("ID:");
        lblID.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblID.setForeground(new Color(102, 153, 255));
        pnlCenter.add(lblID, gbcIDLabel);							// Προσθήκη της ετικέτας στο pnlCenter(στο κεντρικό πάνελ) σύμφωνα με τις ρυθμίσεις του GridBagConstraints(που ορίστηκαν πάνω).

        GridBagConstraints gbcIDField = (GridBagConstraints) baseGbc.clone();
        gbcIDField.gridx = 1;			// 2η στήλη
        gbcIDField.gridy = 1;			// 2η γραμμή
        gbcIDField.anchor = GridBagConstraints.WEST;	// Ευθυγράμμιση προς τα αριστερά(WEST).
        JLabel lblIDAuto = new JLabel("Auto-Complete");
        lblIDAuto.setForeground(new Color(102, 153, 255));
        lblIDAuto.setBackground(new Color(230, 230, 230));
        lblIDAuto.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblIDAuto.setPreferredSize(new Dimension(120, 25));		// Για το συγκεκριμένο component καθορίζεται το προτιμώμενο μέγεθος αυτού του συστατικού μέσω της override μεθόδου setPreferedSize(η οποία ανήκει στο πακέτο javax.swing και στην κλάση JComponent), διασφαλίζεται ένα προτιμώμενο μέγεθος για το συγκεκριμένο component από τον προγραμματιστή κάτι το οποίο λαμβάνει υπόψιν του ο διαχειριστής διάταξης για την τοποθέτηση των στοιείων μέσα στο container. Έτσι το αντικείμενο Dimension λαμβάνει σαν παράμετρο εισόδου 2 αριθμητικές τιμές, οι οποίες αντιστοιχούν σε επιθυμητές διαστάσεις στα 120 pixels πλάτος και στα 25 pixels ύψος.
        pnlCenter.add(lblIDAuto, gbcIDField);				// Προσθήκη του πεδίου κειμένου(textID) στο κεντρικό πάνελ(pnlCenter) με τις καθορισμένες ρυθμίσεις που ορίστηκαν σύμφωνα με το GridBagConstraints για WEST.

        // Εικόνα (Icon)
        GridBagConstraints gbcPhoto = (GridBagConstraints) baseGbc.clone();
        gbcPhoto.gridx = 2;				// 3η στήλη
        gbcPhoto.gridy = 1;				// 2η γραμμή
        gbcPhoto.gridheight = 3;  		// Το στοιχείο σύμφωνα με το gridheight(καθορίζει τις γραμμές που θα καταλαμβάνει το component) όπου απλώνεται σε 3 διαδοχικές γραμμές οριζόντια και σύμφωνα με το layout, δημιουργώντας έναν μεγαλύτερο χώρο για την εικόνα(ανάδειξη της εικόνας σε σχέση με τα υπόλοιπα στοιχεία) σύμφωνα και με το κατάλληλο ύψος που θα έχει η εικόνα με απώτερο σκοπό την ευθυγράμμιση με τα υπόλοιπα στοιχεία και την κάλυψη κάθετου χώρου.
        gbcPhoto.anchor = GridBagConstraints.CENTER;		// Εφαρμογή ευθυγράμμισης και τοποθέτησης στο κέντρο(CENTER) για το αντικείμενο με ορισμό του anchor σε CENTER.
        JLabel lblPhoto = new JLabel();					// Δημιουργία ετικέτας για να τοποθετήσω την εικόνα πάνω στην ετικέτα.
        lblPhoto.setIcon(new ImageIcon(New_Register.class.getResource("/exetastiki_earino/RegisterImage/digital-identity.png")));		// Εδώ φορτώνεται η εικόνα σύμφωνα με το καθορισμένο path μέσω της μεθόδου getResource.
        pnlCenter.add(lblPhoto, gbcPhoto);			// Προσθήκη της ετικέτας στο κεντρικό πάνελ(pnlCenter), σύμφωνα με τα καθορισμένα constraints(που εφαρμόστηκαν πάνω).

        // Name Label & TextField
        GridBagConstraints gbcNameLabel = (GridBagConstraints) baseGbc.clone();
        gbcNameLabel.gridx = 0;			// 1η στήλη
        gbcNameLabel.gridy = 2;			// 3η γραμμή/σειρά
        gbcNameLabel.anchor = GridBagConstraints.EAST;			// Δεξιά(EAST) ευθυγράμμιση
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblName.setForeground(new Color(102, 153, 255));
        pnlCenter.add(lblName, gbcNameLabel);			// Προσθήκη της ετικέτας στο κεντρικό πάνελ(pnlCenter).

        GridBagConstraints gbcNameField = (GridBagConstraints) baseGbc.clone();
        gbcNameField.gridx = 1;		// 2η στήλη
        gbcNameField.gridy = 2;		// 3η γραμμή/σειρά
        gbcNameField.anchor = GridBagConstraints.WEST;			// Αριστερή ευθυγράμμιση
        textName = new JTextField(10);
        textName.setBackground(new Color(230, 230, 230));
        textName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textName.setPreferredSize(new Dimension(120, 25));			// Για το συγκεκριμένο component καθορίζεται το προτιμώμενο μέγεθος αυτού του συστατικού μέσω της override μεθόδου setPreferedSize(η οποία ανήκει στο πακέτο javax.swing και στην κλάση JComponent), διασφαλίζεται ένα προτιμώμενο μέγεθος για το συγκεκριμένο component από τον προγραμματιστή κάτι το οποίο λαμβάνει υπόψιν του ο διαχειριστής διάταξης για την τοποθέτηση των στοιείων μέσα στο container. Έτσι το αντικείμενο Dimension λαμβάνει σαν παράμετρο εισόδου 2 αριθμητικές τιμές, οι οποίες αντιστοιχούν σε επιθυμητές διαστάσεις στα 120 pixels πλάτος και στα 25 pixels ύψος.
        pnlCenter.add(textName, gbcNameField);			// Προσθήκη του πεδίου κειμένου(JTextField-textName) στο κεντρικό πάνελ(pnlCenter) σύμφωνα με τις καθορισμένες constraints ρυθμίσεις.

        // Last Name Label & TextField
        GridBagConstraints gbcLNameLabel = (GridBagConstraints) baseGbc.clone();
        gbcLNameLabel.gridx = 0;		// 1η στήλη
        gbcLNameLabel.gridy = 3;		// 4η γραμμή/σειρά
        gbcLNameLabel.anchor = GridBagConstraints.EAST;			// Δεξιά ευθυγράμμιση
        JLabel lblLName = new JLabel("Last Name:");
        lblLName.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblLName.setForeground(new Color(102, 153, 255));
        pnlCenter.add(lblLName, gbcLNameLabel);				// Προσθήκη της ετικέτας (lblName) στο κεντρικό πάνελ(δηλαδή στο pnlCenter) σύμφωνα και με τις καθορισμένες constraints ρυθμίσεις.

        GridBagConstraints gbcLNameField = (GridBagConstraints) baseGbc.clone();
        gbcLNameField.gridx = 1;		// 2η στήλη
        gbcLNameField.gridy = 3;		// 4η γραμμή/σειρά
        gbcLNameField.anchor = GridBagConstraints.WEST;			// Αριστερή ευθυγράμιση
        textLName = new JTextField(10);						// Δημιουργία πεδίου εισαγωγής για το επώνυμο
        textLName.setBackground(new Color(230, 230, 230));
        textLName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textLName.setPreferredSize(new Dimension(120, 25));			// Για το συγκεκριμένο component καθορίζεται το προτιμώμενο μέγεθος αυτού του συστατικού μέσω της override μεθόδου setPreferedSize(η οποία ανήκει στο πακέτο javax.swing και στην κλάση JComponent), διασφαλίζεται ένα προτιμώμενο μέγεθος για το συγκεκριμένο component από τον προγραμματιστή κάτι το οποίο λαμβάνει υπόψιν του ο διαχειριστής διάταξης για την τοποθέτηση των στοιείων μέσα στο container. Έτσι το αντικείμενο Dimension λαμβάνει σαν παράμετρο εισόδου 2 αριθμητικές τιμές, οι οποίες αντιστοιχούν σε επιθυμητές διαστάσεις στα 120 pixels πλάτος και στα 25 pixels ύψος.
        pnlCenter.add(textLName, gbcLNameField);					// Προσθήκη του JTextField (textLName) στο κεντρικό πάνελ(δηλαδή στο pnlCenter) σύμφωνα και με τις καθορισμένες constraints ρυθμίσεις.

        // Course Label & JComboBox
        GridBagConstraints gbcCourseLabel = (GridBagConstraints) baseGbc.clone();
        gbcCourseLabel.gridx = 0;		// 1η στήλη
        gbcCourseLabel.gridy = 4;		// 5η γραμμή/σειρά
        gbcCourseLabel.anchor = GridBagConstraints.EAST;		// Στοίχηση της ετικέτας(course label) προς τα δεξιά του κελιού.
        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblCourse.setForeground(new Color(102, 153, 255));
        pnlCenter.add(lblCourse, gbcCourseLabel);		// Προσθήκη του αντικειμένου (JLabel-lblCourse) στο κεντρικό πάνελ που αφορά την λίστα επιλογών με τα μαθήματα που θα επιλέγει ο χρήστης της εφαρμογής σύμφωνα με τους περιορισμούς που έχουν οριστεί για το gbcCourseLabel(εφαρμόζοντας το GridBagLayout).

        // Δημιουργία του JComboBox και φόρτωση των μαθημάτων.
        JComboBox<String> courseComboBox = new JComboBox<>();		// Δημιουργία ενός νέου αντικειμένου δηλαδή ενός πτυσσόμενου πλαισίου/κατερχόμενης λίστας (JComboBox), το οποίο είναι ένα ειδικό πλαίσιο που παρέχει μία σειρά επιλογών(στην προκειμένη περίπτωση περιέχει τιμές τύπου String-αλφαριθμητικά-που αναπαριστούν τα μαθήματα του κάθε καθηγητή) για να διαλέξει ο χρήστης της εφαρμογής καθώς είναι ένα light component ενώ κληρονομείται από την κλάση JComponent.
        courseComboBox.setModel(new DefaultComboBoxModel(new String[] {"Java", "OOP with C#", "Python", "C++", "Databases SQL", "Introduction to C"}));		// Σε αυτό το σημείο δημιουργείται ένας πίνακας από αντικείμενα τύπου String (δηλαδή αλφαριθμητικού τύπου) αντιπροσωπεύοντας τις σταθερές επιλογές που έχει στην διάθεσή του ο χρήστης της εφαρμογής με αποτέλεσμα να εμφανίζονται στην αναδυόμενη λίστα(JComboBox) ενώ μέσω χρήσης του τελεστή new DefaultComboBoxModel πραγματοποιείται η δημιουργία ενός αντικειμένου μοντέλου λαμβάνοντας ως παράμετρο εισόδου τον πίνακα με τις σταθερές επιλογές των μαθημάτων που αναλύθηκε προηγουμένως καθώς μέσω αυτού του μοντέλου θα εμφανίζονται οι διαθέσιμες αυτές επιλογές στο JComboBox. Με τρέχον αντικείμενο το courseComboBox το οποίο είναι τύπου JComboBox(αντικείμενο αυτής της κλάσης) καλείται η μέθοδος setModel μέσω της οποίας καθορίζεται το μοντέλο που δημιουργήθηκε για το τρέχον αντικείμενο προκειμένου να αναπαρασταθούν τα δεδομένα στην οθόνη και κατ'επέκταση στην αναπτυσσόμενη λίστα(JComboBox-μηχανισμός πολλαπλής επιλογής), αφού όταν ο χρήστης ανοίξει την λίστα θα δει τις τιμές που υπάρχουν μέσα στον πίνακα.  
        courseComboBox.setToolTipText("Courses");			// Μικρό πληροφοριακό κείμενο που για όσο παραμένει ο χρήστης με το ποντίκι του πάνω στο πτυσσόμενο πλαίσιο(κάνει hover δηλαδή) του αναφέρει ότι είναι Courses αυτό το πεδίο.
        courseComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));		// Καθορισμός γραμματοσειράς.
        courseComboBox.setPreferredSize(new Dimension(120, 25));		// Καθορισμός προτινόμενου μεγέθους με πλάτος 120 pixels και με ύψος 25 pixels.

        GridBagConstraints gbcComboBox = (GridBagConstraints) baseGbc.clone();		// Εδώ πραγματοποιείται κλωνοποίηση του αρχικού αντικειμένου (GridBagConstraints) προκειμένου να καθοριστούν συγκεκριμένες ρυθμίσεις συμβατές με το GridBagLayout προκειμένου να τροποποιηθεί με αυτόν τον συγκεκριμένο τρόπο το αντικείμενο JComboBox.
        gbcComboBox.gridx = 1;		// Τοποθέτηση του ComboBox στην δεύτερη στήλη του πλέγματος.
        gbcComboBox.gridy = 4;		// Τοποθέτηση του ComboBox στην 5η γραμμή, δηλαδή ακριβώς στην ίδια σειρά με την ετικέτα (JLabel lblCourse) μέσω οριζόντιας εμφάνισης και των 2 στοιχείων (components) στην ίδια γραμμή.
        gbcComboBox.anchor = GridBagConstraints.WEST;		// Στοίχηση του ComboBox ως προς τα αριστερά μέσα στο κελί.
        pnlCenter.add(courseComboBox, gbcComboBox);			// Προσθήκη του courseComboBox στο κεντρικό panel σύμφωνα και με τους περιορισμούς βάσει GridBagConstraints που έχουν οριστεί.
        
        // Κουμπί "Insert"
        GridBagConstraints gbcButton = (GridBagConstraints) baseGbc.clone();
        gbcButton.gridx = 0;			// Τοποθέτηση στην 1η στήλη
        gbcButton.gridy = 5;			// Τοποθέτηση στην 6η γραμμή/σειρά
        gbcButton.gridwidth = 3;		// Ορίζει ότι θα καταλαμβάνει 3 στήλες (gridwidth = 3) το συστατικό.  H παράμετρος gridwidth της κλάσης GridBagConstraints εξασφαλίζει την επέκταση του κουμπιού σε 3 γειτονικές στήλες μέσα στο grid στο container καταλαμβάνοντας το μέγιστο πλάτος με ευδιάκριτη οπτική εμφάνιση του κουμπιού λόγω του κεντραρίσματος.
        gbcButton.anchor = GridBagConstraints.CENTER;			// Κεντραρισμένη ευθυγράμμιση
        
        JButton btnInsert = new JButton("Insert");				// Δημιουργία καινούργιου αντικειμένου JButton.
        btnInsert.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Έλεγχος εγκυρότητας μέσω της μεθόδου isEmpty επιστρέφοντας True εάν το μήκος του αλφαριθμητικού ισοδυναμεί με το 0 αλλιώς επιστρέφει false: Εάν κάποιο από τα πεδία είναι κενό αφού γίνει λήψη του περιεχομένου μέσω της μεθόδου getText(), όπου σε περίπτωση που ισχύσει η μία από τις 2 συνθήκες τότε εκτελείται το σώμα της if και ενημερώνεται ο χρήστης μέσω εμφάνισης σχετικού μηνύματος για συμπλήρωση όλων των απαιτούμενων πεδίων, διασφαλίζοντας πώς ο χρήστης θα πρέπει να εισάγει και το όνομα και το επώνυμο προτού επιχειρήσει εισαγωγή στην βάση δεδομένων, διότι εάν λείπει κάποια τιμή δεν πραγματοποιείται η καταχώρηση στην βάση δεδομένων.
                if (textName.getText().isEmpty() || textLName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Please, insert all the required fields: Name and Last Name!", "Missing Data", JOptionPane.WARNING_MESSAGE);
                    return;  // Εξόδος από το event handler και άμεση έξοδος από την μέθοδο actionPerformed χωρίς να συνεχίσει η καταχώρηση, εφόσον ένας από τους 2 ελέγχους εγκυρότητας απέτυχε, που σημαίνει ότι σε μία από τις 2 συνθήκες λάβαμε τιμή true(άρα είναι άδειο το συγκεκριμένο πεδίο) και στην άλλη συνθήκη λάβαμε τιμή false με αποτέλεσμα να βγαίνει όλη η συνθήκη false προκαλώντας τον τερματισμό και την μή υλοποίηση εισαγωγής στην βάση δεδομένων.
                }
        		
             // Εφαρμογή της μεθόδου(Digits) πάνω στα πεδία firstname & lastname κι έλεγχος για το εάν κάποιο από τα πεδία περιέχει αριθμητικά ψηφία.
                String firstname = textName.getText();			// Μέσω της μεθόδου getText() γίνεται λήψη του περιεχομένου που εισήγαγε ο χρήστης στο JTextField πεδίο textName (που αφορά το firstname που πληκτρολόγησε ο χρήστης) κι εκχώρηση αυτού σε μία μεταβλητή τύπου String.
                String lastname  = textLName.getText();
                if (Digits(firstname) || Digits(lastname)) {	// Σε μία δομή διακλάδωσης if κι αφού έχουν δημιουργηθεί οι 2 μεταβλητές (firstname & lastname) που είναι τύπου String καλείται η μέθοδος Digits(η οποία δέχεται ως είσοδο ένα αντικείμενο τύπου String εξ ου που και στην παράμετρο της μεθόδου μπαίνουν οι 2 μεταβλητές που φτιάξαμε παραπάνω και είναι τύπου String) καθώς ελέγχεται μέσω της λογικής διάζευξης (OR ||) αν τουλάχιστον 1 από τις 2 κλήσεις της μεθόδου επιστρέψει αποτέλεσμα (true-που σημαίνει ότι βρέθηκε κάποιο αριθμητικό ψηφίο), τότε θα εκτελεστεί το σώμα της if και θα ενημερωθεί ο χρήστης για την μή επιτυχή εγγραφή στην βάση δεδομένων λόγω του ότι περιέχονται αριθμητικά ψηφία είτε στο πεδίο συμπλήρωσης για το firstname είτε στο πεδίο συμπλήρωσης για το lastname είτε και στα 2 πεδία ενδεχομένως να έχουν συμπληρωθεί αριθμητικά ψηφία. 
                    JOptionPane.showMessageDialog(Main_Application.register, "Unsuccessful registration! Please insert only characters and not numbers!", "Unsuccessful registration", JOptionPane.WARNING_MESSAGE);
                    return;		// Με την εντολή return διακόπτεται η εκτέλεση της μεθόδου και της όλης διαδικασίας(που υπάρχει μέσα στην δομή διακλάδωσης if) που σημαίνει ότι σε ένα από τα 2 πεδία για την συμπλήρωση του firstname και για την συμπλήρωση του lastname έχει βρεθεί αριθμητικό ψηφίο κι αυτό έχει ως αποτέλεσμα ο κώδικας να μην συνεχιστεί παρακάτω για την καταχώρηση της εγγραφής(αφού δεν έχει νόημα από την στιγμή που έχουν βρεθεί αριθμητικά ψηφία στα πεδία firstname και lastname να προχωρήσουμε σε εγγραφή στην βάση δεδομένων). 
                }
                
                try {
        		Connection myconn = Application.conn;		// Σε αυτό το σημείο μπορούμε να δούμε το αντικείμενο της σύνδεσης, όπου πατώντας ο χρήστης το κουμπί insert θα καταχωρείται μία νέα εγγραφή στην βάση δεδομένων. Εκχωρώ το αντικείμενο που βρίσκεται μέσα στο πεδίο αποθηκευμένο της κλάσης Application σε μία τοπική μεταβλητή myconn τύπου Connection. Αυτή η τοπική μεταβλητή myconn που είναι τύπου Connection θα χρησιμεύσει για την αποστολή sql ερωτημάτων στην βάση δεδομένων.
        		String checkSql = "SELECT COUNT(*) FROM teachers WHERE firstname = ? AND lastname = ?";		// Έλεγχος για υπάρχουσα εγγραφή πριν από την εισαγωγή. Στην προκειμένη περίπτωση μέσω της εντολής SELECT θα καταμετρηθεί ο αριθμός των εγγραφών στον πίναα teachers που έχουν το ίδιο firstname και lastname σύμφωνα με αυτά που εισήχθησαν από τον χρήστη, διότι με αυτόν τον τρόπο πραγματοποιείται ο έλεγχος διπλότυπων εγγραφών με απώτερο σκοπό να μην εισαχθεί εγγραφή που υπάρχει ήδη στην βάση δεδομένων.
        		PreparedStatement check = myconn.prepareStatement(checkSql);		// Δημιουργία PreparedStatement για το sql ερώτημα που τέθηκε πάνω.
        		check.setString(1, textName.getText());			// Ορίζεται η πρώτη τιμή που αντικαθιστά το πρώτο ? από το sql query που τέθηκε πάνω αντιπροσωπεύοντας το firstname που ο χρήστης εισήγαγε και θα συγκριθεί με τις εγγραφές της βάσης.
        		check.setString(2, textLName.getText());		// Κι εδώ ορίζεται η δέυτερη τιμή που αντικαθιστά το δεύτερο ? αντιπροσωπεύοντας το last name που εισήγαγε ο χρήστης και σύγκριση αυτού με τις εγγραφές της βάσης δεδομένων σε περίπτωση διπλότυπης εγγραφής.
        		ResultSet rs = check.executeQuery();			// Εκτέλεση του sql ερωτήματος μέσω κλήσης της μεθόδου executeQuery() που υπάρχει μέσα στο PreparedStatement και αποστολή του query στον sql server(της βάσης δεδομένων) για εκτέλεση του query κι εκχώρηση σε μία μεταβλητή rs τύπου ResultSet αφού μέσω εκτέλεσης της μεθόδου executeQuery() επιστρέφεται ένα αντικείμενο τύπου ResultSet(όπου όλες οι εγγραφές εμφανίζονται σε μορφή πίνακα με σειρές και στήλες). Το ερώτημα όπως έχει διατυπωθεί και πάνω περιλαμβάνει το COUNT(*) query μέσω του οποίου θα παρέχεται από το ResultSet μόνο μία γραμμή και μία στήλη(δηλαδή το αποτέλεσμα του COUNT(*) που θα προκύψει).
        		
        		if(rs.next() && rs.getInt(1) > 0){		// Σε αυτό το σημείο ελέγχονται τα αποτελέσματα του ερωτήματος που εκτελέστηκαν στο PreparedStatement από τον πίνακα teachers σύμφωνα με το firstname και το lastname, όπου μέσω κλήσης της μεθόδου rs.next πραγματοποιείται μετακίνηση του δείκτη στην πρώτη εγγραφή(στην περίπτωση που υπάρχει εγγραφή τότε επιστρέφεται αποτέλεσμα true). Στο δέυτερο σκέλος της συνθήκης μέσω κλήσης της μεθόδου getInt(1) γίνεται λήψη τιμής για την πρώτη στήλη που είναι και το αποτέλεσμα της συνάρτησης COUNT(*) και στην συνέχεια ελέγχεται μέσω του rs.getInt(1) > 0 εάν υπάρχει τουλάχιστον 1 εγγραφή με το ίδιο firstname και lastname που έχει πληκτρολογήσει ο χρήστης της εφαρμογής. Εάν και οι 2 έλεγχοι επιστρέψουν αποτέλεσμα true, δηλαδή υπάρχει εγγραφή στην πρώτη σειρά του πίνακα και ταυτόχρονα (λογικό &&) υπάρχει ήδη υπάρχουσα εγγραφή με τα ίδια στοιχεία τότε εκτελείται το σώμα της if κι ενημερώνεται ο χρήστης της εφαρμογής ότι υπάρχει ήδη στην βάση δεδομένων καθηγητής με τα ίδια στοιχεία και δεν προχωράει η διαδικασία της εγγραφής. 
        		    JOptionPane.showMessageDialog(contentPane, "This teacher already exists in the database.", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
        		    check.close();		// Κλείσιμο του PreparedStatement check για το query ελέγχου διπλότυπων εγγραφών.
        		    return;				// Με την εντολή return διακόπτεται η εκτέλεση του τρέχοντος block της if, διότι βρέθηκε εγγραφή με τα ίδια δεδομένα και δεν συνεχίζεται η διαδικασία της εισαγωγής στην βάση δεδομένων. 
        		}
        		check.close();
        		
        		String sql = "INSERT INTO teachers (firstname, lastname) VALUES (?, ?)";	 // Μέσω της εντολής INSERT πραγματοποιείται η εισαγωγή νέων γραμμών/νέων εγγραφών στον πίνακα teachers της σχεσιακής βάσης δεδομένων καθώς τα ? για τα πεδία firstname και lastname θα αντικατασταθούν από τις τιμές που θα εισάγει ο ίδιος ο χρήστης της εφαρμογής.
        		PreparedStatement ps = myconn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);		// Δημιουργία του PreparedStatement με το SQL ερώτημα που ορίστηκε στην μεταβλητή sql ως πρώτο όρισμα της μεθόδου prepareStatement κι ως δεύτερο όρισμα μέσω χρήσης της σταθεράς RETURN_GENERATED_KEYS επιστρέφονται τα auto-generated keys που δημιουργούνται από την βάση δεδομένων(MySQL Workbench) αφού και το πεδίο id εντός της βάσης δεδομένων έχει δηλωθεί ως auto-increment(που σημαίνει ότι μπαίνει αυτόματα από το workbench η συμπλήρωση του συγκεκριμένου πεδίου και δεν συμπληρώνεται από τον ίδιο τον χρήστη της εφαρμογής κατά την διαδικασία εγγραφής) κι έτσι πραγματοποιείται ανάκτηση του id για την συγκεκριμένη νέα εγγραφή.
        		ps.setString(1, textName.getText());			// Τίθεται η τιμή που θα αντικαταστήσει το πρώτο ? που υπάρχει στο textName κι αφορά το firstname της εγγραφής που επιθυμεί να εισάγει ο χρήστης.
        		ps.setString(2, textLName.getText());
        		int data = ps.executeUpdate();			// Στέλνει την SQL εντολή εισαγωγής στον server, κι αυτός μέσω της μεθόδου executeUpdate του PreparedStatement επιστρέφει έναν ακέραιο που δείχνει πόσες εγγραφές ενημερώθηκαν, δηλαδή πόσες εγγραφές δημιουργήθηκαν κι εάν η εισαγωγή είναι επιτυχής, ο αριθμός τότε θα είναι ≥ 1.
        		if(data > 0) {			// Σε αυτό το σημείο ελέγχεται εάν ο αριθμός που επέστρεψε το executeUpdate() (αποθηκευμένος στη μεταβλητή data) είναι μεγαλύτερος από 0, δηλαδή αν πραγματοποιήθηκε τουλάχιστον μία εγγραφή και στην περίπτωση που πάρουμε true από αυτήν την συνθήκη τότε εκτελείται το σώμα της if κι ενημερώνεται ο χρήστης μέσω ενός pop-up παραθύρου για την επιτυχή καταχώρηση/εισαγωγή μίας νέας εγγραφής στην βάση δεδομένων.
        	        ResultSet keys = ps.getGeneratedKeys();		// Καλείται η μέθοδος getGeneratedKeys για το αντικείμενο του PreparedStatement που είναι το ps με σκοπό να ανακτηθούν τα auto-generated keys που δημιουργήθηκαν κατά την διαδικασία εισαγωγής ενός νέου καθηγητή στην βάση δεδομένων καθώς το αυτόματο δημιουργημένο ID αποθηκεύεται στο αντικείμενο keys που είναι τύπου ResultSet. Εάν το αντικείμενο Statement δεν δημιούργησε κανένα κλειδί επιστρέφεται ένα κενό αντικείμενο τύπου ResultSet.
        			if(keys.next()) {					// Σε αυτό το σημείο ελέγχεται εάν υπάρχει όντως ένα αποτέλεσμα ResultSet καλώντας την μέθοδο next() πάνω στο αντικείμενο keys, το οποίο αντικείμενο είναι τύπου ResultSet που περιέχει τα auto-generated keys που δημιουργήθηκαν κατά την εκτέλεση του SQL ερωτήματος εισαγωγής(δηλαδή μέσω της εντολής insert για καταχώρηση ενός νέου καθηγητή). Μέσω χρήσης της μεθόδου next() που επιστρέφει μία boolean τιμή και στην προκειμένη περίπτωση επιστρέφεται true που σημαίνει ότι υπάρχει μία εγγραφή για την οποία έχει δημιουργηθεί ένα auto-generated key (δηλαδή το νέο ID της εγγραφής).
        	            int regId = keys.getInt(1);		// Σε αυτό το σημείο ο χρήστης γνωστοποιείται για το νέο ID που δόθηκε στην καινούργια εγγραφή καθηγητή καθώς μέσω χρήσης της μεθόδου getInt διαβάζεται η πρώτη στήλη της τρέχουσας εγγραφής του ResultSet όπου τίθεται ως παράμετρος εισόδου στην συγκεκριμένη μέθοδο ο αριθμός 1(που στην ουσία αναφέρεται ως δείκτης 1 και αντιπροσωπεύει την πρώτη στήλη του ResultSet) και περιέχει το auto-generated id (που παράχθηκε από την βάση δεδομένων μέσω του μηχανισμού auto-increment και αποτελεί έναν ακέραιο αριθμό).  
        	
                        String selectedCourse = (String) courseComboBox.getSelectedItem();		// Για το τρέχον αντικείμενο courseComboBox καλείται η μέθοδος getSelectedItem() μέσω της οποίας επιστρέφεται το τρέχον επιλεγμένο στοιχείο(δηλαδή η τρέχουσα επιλογή από την αναπτυσσόμενη λίστα) ενώ ταυτόχρονα πραγματοποιείται μετατροπή της τιμής σε String(κάτι το οποίο αντιπροσωπεύει το μάθημα που έχει επιλέξει ο χρήστης της εφαρμογής) κι όλο αυτό εκχωρείται σε μία μεταβλητή αντίστοιχου τύπου, δηλαδή τύπου String(αλφαριθμητικού τύπου).
                        if(selectedCourse != null && !selectedCourse.isEmpty()) {			// Σε αυτό το σημείο μέσω της δομής διακλάδωσης if ελέγχεται εάν η μεταβλητή selectedCourse(η οποία έχει εκχωρημένο μέσα της την τρέχουσα επιλογή/το τρέχον στοιχείο που υπάρχει μέσα στο ComboBox-στην αναπτυσσόμενη λίστα-κι αφορά κάποιο από τα μαθήματα που φαίνονται στην λίστα) δεν είναι null (στην περίπτωση που δεν ληφθεί σωστά η τιμή από το JComboBox και προς αποφυγή κάποιου NullPointerException) καθώς κι εάν ταυτόχρονα(λογικό ΚΑΙ &&-καθορίζοντας ότι πρέπει και τα 2 σκέλη της συνθήκης να ισχύουν για να προχωρήσει παρακάτω ο κώδικας και να εκτελεστεί το σώμα της if για την εισαγωγή της σχέσης με τον πίνακα teacher_courses, δηλαδή την εισαγωγή συσχέτισης του καθηγητή με το συγκεκριμένο μάθημα που διδάσκει) η μεταβλητή selectedCourse δεν είναι κενή(δηλαδή δεν αποτελείται από κενό αλφαριθμητικό). Στην περίπτωση που επιστραφεί τιμή false σε ένα από τα δύο σκέλη της συνθήκης(δηλαδή είτε η μεταβλητή είναι null είτε η μεταβλητή περιέχει κενό αλφαριθμητικό) τότε δεν εκτελείται το σώμα της if. 
                            String courseSql = "INSERT INTO teacher_courses (teacher_id, course_id) " + "VALUES (?, (SELECT id FROM courses WHERE course_name = ?))";		// Εισαγωγή μίας νέας εγγραφής μέσω της εντολής INSERT INTO στον πίνακα teachers για τον συσχετισμό του πίνακα αυτού με τον πίνακα teacher_courses(ο οποίος καθορίζει τα μαθήματα που διδάσκει ο κάθε καθηγητής) καθώς όπως έχει δημιουργηθεί και στην αντίστοιχη βάση δεδομένων αποτελείται από 2 στήλες(teacher_id & course_id-όπου σε αυτές τις 2 στήλες θα εισαχθούν τιμές). Ταυτόχρονα το πρώτο ? (το οποίο αναπαριστά το teacher_id) θα αντικατασταθεί μέσω του PreparedStatement από το αυτόματα δημιουργημένο ID (auto-increment id-όπως δηλώθηκε από την βάση δεδομένων) που αφορά τον κάθε καθηγητή από τον πίνακα teachers. Μέσω χρήσης του subquery (SELECT id FROM courses WHERE course_name = ?) εξασφαλίζεται ότι δεν εισάγεται απευθείας η τιμή για το course id, διότι αναζητείται από τον πίνακα courses η εγγραφή με το όνομα του μαθήματος που στην ουσία θα αντικατασταθεί από την τιμή που θα εισαχθεί στο δεύτερο ερωτηματικό (?). Πιο συγκεκριμένα η χρήση αυτού του subquery διασφαλίζει την ανάκτηση του id του μαθήματος που θεωρείται και primary key από τον πίνακα courses για το συγκεκριμένο μάθημα (course_name) καθώς το course_id προσδιορίζεται αναλόγως του τί επιλογή έχει κάνει ο χρήστης ανάλογα με το μάθημα που έχει επιλέξει να συνδέσει την καταχώρηση ενός καινούργιου καθηγητή.  
                            PreparedStatement courseps = myconn.prepareStatement(courseSql);		// Κλήση της μεθόδου prepareStatement πάνω στο αντικείμενο σύνδεσης myconn βάζοντας ως παράμετρο εισόδου την εντολή sql που αναλύθηκε προηγουμένως(στην πάνω γραμμή του κώδικα) και είναι εκχωρημένη μέσα στην μεταβλητή courseSql.
                            courseps.setInt(1, regId);				// Μέσω της μεθόδου setInt πάνω στο αντικείμενο courseps (το οποίο είναι τύπου PreparedStatement) αντικαθίσταται το πρώτο ? (εξ ου και ο δείκτης 1, αφού η αρίθμηση σε αυτές τις περιπτώσεις ξεκινάει από το 1, όπως έχει περιγραφεί και προηγουμένως), το οποίο αντιστοιχεί στο teacher_id του πίνακα teacher_courses (σύμφωνα και με την ισχύουσα βάση δεδομένων) καθώς εξασφαλίζεται η σωστή συσχέτιση του καθηγητή με το μάθημα που διδάσκει μέσω χρήσης της μεταβλητής regId ως δεύτερη παράμετρο της μεθόδου, η οποία μεταβλητή περιέχει το αυτόματα δημιουργημένο ID-auto increment id (που φτιάχνει το SQL Workbench για τον κάθε καθηγητή) το οποίο καταχωρείται στον πίνακα teachers ενώ ταυτόχρονα γίνεται λήψη αυτού του auto-generated id από την μέθοδο getGeneratedKeys() κατά την εισαγωγή/εγγραφή ενός νέου καθηγητή στην βάση δεδομένων.
                            courseps.setString(2, selectedCourse);		// Σε αυτό το σημείο μέσω χρήσης της μεθόδου setString πάνω στο αντικείμενο courseps του PreparedStatement αντικαθίσταται το δεύτερο ? (εξ ου και ο δείκτης 2) όπου πραγματοποιείται η λήψη της αντίστοιχης τιμής μέσω χρήσης του ακόλουθου subquery: (SELECT id FROM courses WHERE course_name = ?), όπου μέσω αυτού του ερωτήματος γίνεται προσπάθεια ανάκτησης του αντίστοιχου course_id από τον πίνακα courses ενώ ταυτόχρονα ως δεύτερη παράμετρος της μεθόδου τίθεται η μεταβλητή selectedCourse, η οποία αντιπροσωπεύει το όνομα του μαθήματος από την αναπτυσσόμενη λίστα (JComboBox) που έχει επιλέξει ο χρήστης της εφαρμογής.
                            courseps.executeUpdate();		// Μέσω της μεθόδου executeUpdate() πραγματοποιείται αποστολή και εκτέλεση του sql ερωτήματος στον sql server, όπου επιστρέφεται ένας ακέραιος αριθμός ο οποίος καθορίζει πόσες γραμμές από τον πίνακα επηρρεάστηκαν με αποτέλεσμα την επιτυχή δημιουργία μίας νέας εγγραφής στον πίνακα teacher_courses καθορίζοντας την αποτελεσματική συσχέτιση του εκάστοτε καθηγητή με το μάθημα στην περίπτωση επιτυχούς εκτέλεσης του query. 
                            courseps.close();			// Σε αυτό το σημείο μέσω χρήσης της μεθόδου close() πραγματοποιείται κλείσιμο του αντικειμένου courseps (δηλαδή του PreparedStatement που χρησιμοποιήθηκε για την εκτέλεση του query) απελευθερώνοντας τους πόρους που δεσμεύτηκαν κατά την εκτέλεση αυτής της διαδικασίας διασφαλίζοντας την ορθή διαχείριση των πόρων προς αποφυγή δέσμευσης πλεονάζοντος χώρου στην μνήμη.
                        }
        	            JOptionPane.showMessageDialog(Main_Application.register, "The registration was successful with ID: " +regId+ "!", "Registration", JOptionPane.INFORMATION_MESSAGE);		// Επιτυχή καταχώρηση της εγγραφής μαζί με το καταχωρημένο ID της νέας εγγραφής.
        	         
        	            // Εκκαθάριση των πεδίων εισαγωγής βάζοντας ως παράμετρο εισόδου στην μέθοδο setText ένα κενό αλφαριθμητικό ώστε να μην παραμένουν οι τιμές της προηγούμενης εγγραφής και να καθαρίζονται τα πεδία firstname & lastname αφού πρώτα πραγματοποιηθεί η εισαγωγή ενός νέου καθηγητή στην βάση δεδομένων.
        	            textName.setText("");
        	            textLName.setText("");
        			}
        			 keys.close();  // Κλείσιμο του ResultSet που χρησιμοποιήθηκε για την ανάκτηση του auto-generated key και απελευθέρωση πόρων που δεσμεύτηκαν από το ResultSet.
        	    }
        	    ps.close();  	// Κλείσιμο του PreparedStatement(που χρησιμοποιήθηκε για την εκτέλεση του SQL ερωτήματος) ώστε να μην δεσμεύονται άλλο οι πόροι που χρησιμοποιήθηκαν για την εκτέλεση του ερωτήματος. Μετά την εκτέλεση της SQL εντολής, δεν θα ξαναχρειαστεί το αντικείμενο (στο οποίο δείχνει η μεταβλητή) ps. Το αντικείμενο αυτό πιάνει χώρο στη μνήμη. Με τη μέθοδο close(), ο χώρος αυτός αποδεσμεύεται και το αντικείμενο "χάνεται".
        	} catch (SQLException ex) {
        			JOptionPane.showMessageDialog(contentPane, "Connection to Database failed.", "Error", JOptionPane.ERROR_MESSAGE);		// Αδυναμία σύνδεσης με την βάση δεδομένων και λόγω λάθους sql ερωτήματος(συντακτικού) ή για οποιονδήποτε άλλο λόγο που δεν καλύπτεται από τα παραπάνω.
        		}
        	}
        });
        
        btnInsert.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnInsert.setBackground(new Color(255, 153, 204));
        btnInsert.setForeground(new Color(0, 51, 102));
        pnlCenter.add(btnInsert, gbcButton);			// Προσθήκη του κουμπιού στο κεντρικό πάνελ(δηλαδή στο pnlCenter) τηρώντας τις καθορισμένες GridBagConstraints ρυθμίσεις που διατυπώθηκαν πάνω.

        GridBagConstraints gbcBottom = (GridBagConstraints) baseGbc.clone();		// Κλωνοποιεί τα βασικά constraints για να δημιουργήσει έναν "κάτω χώρο" (vertical glue) που θα καταλάβει τον επιπλέον κάθετο χώρο στο κάτω μέρος του pnlCenter.
        gbcBottom.gridx = 0;			// Τοποθέτηση αόρατου στοιχείου στην 1η στήλη
        gbcBottom.gridy = 5;			// Τοποθέτηση σε 6η γραμμή/σειρά
        gbcBottom.gridwidth = 3;		// Κάλυψη 3 διαδοχικών στηλών (gridwidth = 3)
        gbcBottom.weighty = 1.0;		// Πλήρη κατανομή του κάθετου χώρου(weighty=1), διότι σε αυτό το σημείο καθορίζεται ο τρόπος/πώς δηλαδή θα μοιραστεί ο ελέυθερος χώρος, διότι μέσω της τιμής 1 που εκχωρείται το στοιχείο filler έχει την δυνατότητα να εκμεταλλευτεί όλο τον διαθέσιμο κάθετο χώρο με την αρμονική τοποθέτηση των υπόλοιπων στοιχείων γύρω από το filler.
        gbcBottom.fill = GridBagConstraints.VERTICAL;			// Σε αυτό το σημείο το αντικέιμενο μέσω χρήσης του GridBagConstraints.VERTICAL, έχει ως σκοπό να καταλάβει όλο το διατιθέμενο ύψος χωρίς την μεταβολή του μήκους του σε κάθετη κατέυθυνση εντός του κελιού που έχει οριστεί.
        pnlCenter.add(Box.createVerticalGlue(), gbcBottom);		// Σε αυτό το σημείο γίνεται προσθήκη(με την μέθοδο add) στο κεντρικό πάνελ(pnlCenter) ενός αόρατου συστατικού με την μέθοδο Box.createVerticalGlue(), όπου το vertigal glue καταναλώνει τον επιπλέον κάθετο χώρο όταν το παράθυρο μεγαλώνει με διατήρηση κατακόρυφου προσανατολισμού των στοιχείων. Το gbcBottom είναι μία μεταβλητή τύπου GridBagConstraints με την κατάληψη σωστής τοποθέτησης του συστατικού μέσα στο πλέγμα και όπως ορίστηκε και παραπάνω καθώς ο σκοπός είναι να καταλαμβάνει τον επιπλέον κάθετο χώρο όταν διατίθεται, έτσι ώστε τα παραπάνω στοιχεία (όπως οι γραμμές με τα πεδία εισαγωγής και κουμπιά) να παραμένουν κεντραρισμένα και ομαλά διατεταγμένα όταν το παράθυρο μεγαλώνει σε ύψος. Με άλλα λόγια, εάν το pnlCenter έχει περισσότερο καθέτο χώρο από ό,τι χρειάζεται, το vertical glue επεκτείνεται για να “συμπληρώσει” το κενό, διατηρώντας την ισορροπία και τη συμμετρία της διάταξης.

        // Τώρα, τοποθετούμε το pnlCenter μέσα σε ένα εξωτερικό container με FlowLayout ώστε να κεντραριστεί οριζόντια και κάθετα στο CENTER του contentPane.
        JPanel centerContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));		// Δημιουργία ενός εξωτερικού JPanel(με όνομα centerContainer) κι εκχώρηση και δημιουργία ενός καινούργιου αντικειμένου JPanel μέσω δημιουργίας κι ενεργοποίησης του διαχειριστή διάταξης FlowLayout και χρήση αυτού του διαχειριστή εντός του container. Πιο συγκεκρμένα ο κατασκευαστής αυτός(FlowLayout(int alignment, int hgap, int vgap)) δημιουργεί έναν καινούργιο FlowLayoutManager με πρώτο όρισμα τον καθορισμό της ευθυγράμμισης(τον τρόπο τοποθέτησης των γραμμών) σε ακέραια τιμή ως FlowLayout.CENTER για το οριζόντιο κεντράρισμα μέσα στον χώρο του container. Στο δεύτερο όρισμα(hgap-οριζόντιο διάκενο μεταξύ των συστατικών αλλά και μεταξύ των συστατικών και των συνόρων του container μέσω καθορισμού της κενής απόστασης των 2 διαδοχικών συστατικών που βρίσκονται στην ίδια γραμμή) διασφαλίζεται ότι δεν υπάρχει επιπλέον κενό μεταξύ των συστατικών στην ίδια γραμμή, λόγω του ότι είναι 0 pixels καθώς και το vgap(κατακόρυφο διάκενο-κενή απόσταση μεταξύ 2 διαδοχικών στοιχέιων που είναι τοποθετημένα στην ίδια στήλη) είναι 0 pixels που σημαίνει ότι αυτή είναι η απόσταση των στοιχείων σε περίπτωση που είναι τοποθετημένα σε περισσότερες από μία γραμμές.  
        centerContainer.setBackground(new Color(255, 229, 204));
     // Μέσω της μεθόδου add γίνεται προσθήκη του pnlCenter μέσα στο centerContainer, και τελικά, το τοποθετεί ως το κεντρικό στοιχείο του contentPane (στη θέση BorderLayout.CENTER).
        centerContainer.add(pnlCenter);						// Μέσω της μεθόδου add πραγματοποιείται προσθήκη του pnlCenter(του κεντρικού δηλαδή panel) μέσα στο κεντρικό container(δηλαδή στο centerContainer) σύμφωνα με τους κανόνες που έχουν οριστεί από τον διαχειριστή διάταξης FlowLayout με hgap=0 και vgap=0 (χωρίς επιπλόν οριζόντια ή κατακόρυφη απόσταση).
        contentPane.add(centerContainer, BorderLayout.CENTER);			// Σε αυτό το σημείο πραγματοποιείται προσθήκη μέσω της μεθόδου add του centerContainer μέσα στο κύριο container όλου του παραθύρου(JFrame) που είναι το contentPane όπου τίθεται ως παράμετρος εισόδου ο διαχειριστής διάταξης BorderLayout.CENTER λέγοντας στην ουσία στον διαχειριστή διάταξης να τοποθετήσει το centerContainer στην κεντρική περιοχή του παραθύρου σύμφωνα και με τον ορισμό διάταξης που έχει καθοριστεί για το contentPane.
    
    }	// Εδώ κλείνει ο κατασκευαστής.
    
    public boolean Digits(String str) {			// Η συγκεκριμένη μέθοδος ελέγχει εάν τα πεδία firstname και lastname περιέχουν τουλάχιστον έναν αριθμητικό χαρακτήρα εξ ου που ο τύπος επιστροφής της μεθόδου είναι boolean(=επιστρέφει μία αληθοτιμή True/False) και δέχεται ως παράμετρο εισόδου ένα αντικείμενο τύπου String.
        for (char ch : str.toCharArray()) {		// Μέσω της επαναληπτικής δομής for και πιο συγκεκριμένα της enhanced for μετατρέπεται το δοσμένο αλφαριθμητικό(που είναι ένα αντικείμενο String ως παράμετρος της μεθόδου) μέσω χρήσης της μεθόδου toCharArray σε έναν πίνακα από χαρακτήρες, του οποίου το μήκος του είναι όσο είναι το μήκος του δοσμένου αλφαριθμητικού με αποτέλεσμα να αναπαρίστανται ως μία ακολουθία από χαρακτήρες ενώ μέσω αυτού του βρόγχου διατρέχεται κάθε χαρακτήρας (ch) του πίνακα διασφαλίζοντας την ανάλυση του κάθε χαρακτήρα ξεχωριστά προκειμένου να ανιχνευθούν ψηφία.
            if (Character.isDigit(ch)) {		// Εντός της επαναληπτικής δομής for χρησιμοποιείται μία δομή διακλάδωσης if μέσω της οποίας ελέγχεται εάν ο τρέχον χαρακτήρας(ch-που μπαινει ως παράμετρος στην μέθοδο Digits) είναι αριθμητικό ψηφίο επιστρέφοντας true εάν πράγματι το ch συμβολίζει αριθμητικό ψηφίο αλλιώς σε διαφορετική περίπτωση επιστρέφει false με απόρροια τον τερματισμό της αναζήτησης από την στιγμή που έχει βρεθεί χαρακτήρας που συμβολίζει αριθμητικό ψηφίο.
                return true;		// Με την εντολή return true διακόπτεται η εκτέλεση της επανάληψης αφού εντοπίστηκε αριθμητικό ψηφίο στον πίνακα κι επιστρέφει αποτέλεσμα true.
            }					// Εδώ κλείνει η συνθήκη που ελέγχει για τους αριθμούς.
        }
        return false;			// Μόλις ολοκληρωθεί η επαναληπτική δομή for κι αφού έχουμε διατρέξει όλο τον πίνακα από χαρακτήρες και είμαστε σίγουροι ότι δεν έχει βρεθεί κάποιο αριθμητικό ψηφίο τότε και μόνο τότε τερματίζεται η εκτέλεση της μεθόδου κι επιστρέφεται αποτέλεσμα false(δηλαδή το δοσμένο κείμενο/String δεν περιέχει αριθμητικά ψηφία).  
    }
    
}		// Εδώ κλείνει η κλάση.
