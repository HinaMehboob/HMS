package Project.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class InventoryFrame extends JFrame implements ActionListener {
    private DefaultListModel<String> diseaseListModel;
    private JList<String> diseaseList;
    private JCheckBox[] diseaseCheckBoxes;
    private JButton diseasesButton;
    private Map<String, String[]> medicineMap;

    public InventoryFrame() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setSize(width - 20, height - 20);
        this.setTitle("Welcome To HMS");
        setIconImage(new ImageIcon(LoginFrame.ICON_PATH).getImage());
        this.setResizable(true);

        // Creating the background panel with an image
        WelcomeBackgroundPanel inventoryPanel = new WelcomeBackgroundPanel(new ImageIcon(LoginFrame.ICON_PATH).getImage());
        inventoryPanel.setLayout(new BorderLayout());

        // Initialize the "Diseases" button
        diseasesButton = new JButton("Diseases");
        diseasesButton.addActionListener(this);
        inventoryPanel.add(diseasesButton, BorderLayout.NORTH);

        // Initialize disease list model
        diseaseListModel = new DefaultListModel<>();

        // List of 100 common diseases
        String[] commonDiseases = {
                "Common Cold", "Influenza (Flu)", "COVID-19", "Malaria", "Diabetes",
                "Hypertension (High Blood Pressure)", "Asthma", "Arthritis", "Osteoporosis",
                "Chronic Obstructive Pulmonary Disease (COPD)", "Tuberculosis (TB)", "HIV/AIDS",
                "Hepatitis B", "Hepatitis C", "Dengue Fever", "Chikungunya", "Zika Virus",
                "Ebola Virus Disease", "Rabies", "Measles", "Chickenpox", "Rubella (German Measles)",
                "Mumps", "Whooping Cough (Pertussis)", "Polio (Poliomyelitis)", "Rotavirus Infection",
                "Norovirus Infection", "Gastroenteritis", "Peptic Ulcer Disease", "Gastritis",
                "Irritable Bowel Syndrome (IBS)", "Crohn's Disease", "Ulcerative Colitis",
                "Appendicitis", "Gallstones", "Kidney Stones", "Urinary Tract Infection (UTI)",
                "Prostatitis", "Benign Prostatic Hyperplasia (BPH)", "Erectile Dysfunction",
                "Breast Cancer", "Lung Cancer", "Colorectal Cancer", "Prostate Cancer",
                "Skin Cancer (Melanoma, Basal Cell Carcinoma, Squamous Cell Carcinoma)",
                "Leukemia", "Lymphoma", "Ovarian Cancer", "Cervical Cancer", "Uterine Cancer",
                "Endometriosis", "Polycystic Ovary Syndrome (PCOS)", "Erectile Dysfunction (ED)",
                "Premature Ejaculation (PE)", "Depression", "Anxiety Disorders", "Bipolar Disorder",
                "Schizophrenia", "Obsessive-Compulsive Disorder (OCD)",
                "Attention-Deficit/Hyperactivity Disorder (ADHD)", "Alzheimer's Disease",
                "Parkinson's Disease", "Multiple Sclerosis (MS)", "Epilepsy", "Stroke",
                "Myocardial Infarction (Heart Attack)", "Heart Failure", "Hypertensive Heart Disease",
                "Atherosclerosis", "Peripheral Artery Disease (PAD)", "Deep Vein Thrombosis (DVT)",
                "Pulmonary Embolism (PE)", "Sickle Cell Disease", "Thalassemia", "Hemophilia",
                "Anemia", "Cirrhosis", "Nonalcoholic Fatty Liver Disease (NAFLD)", "Pancreatitis",
                "Hypothyroidism", "Hyperthyroidism", "Addison's Disease", "Cushing's Syndrome",
                "Fibromyalgia", "Chronic Fatigue Syndrome (CFS)", "Gout", "Rheumatoid Arthritis",
                "Psoriasis", "Eczema", "Vitiligo", "Allergies (Seasonal Allergic Rhinitis, Food Allergies)",
                "Asthma", "Chronic Sinusitis", "Pneumonia", "Bronchitis", "Fibroid Tumors (Uterine Fibroids)",
                "Endometrial Polyps", "Pelvic Inflammatory Disease (PID)",
                "Menstrual Disorders (Dysmenorrhea, Menorrhagia)", "Infertility"
        };

        for (String disease : commonDiseases) {
            diseaseListModel.addElement(disease);
        }

        // Create checkboxes for diseases
        diseaseCheckBoxes = new JCheckBox[commonDiseases.length];
        for (int i = 0; i < commonDiseases.length; i++) {
            diseaseCheckBoxes[i] = new JCheckBox(commonDiseases[i]);
        }

        // Initialize medicine map
        initializeMedicineMap();

        this.add(inventoryPanel);
        this.setVisible(true);
    }

    private void initializeMedicineMap() {
        medicineMap = new HashMap<>();

        medicineMap.put("Common Cold", new String[]{"Cold Medicine A", "Cold Medicine B"});
        medicineMap.put("Influenza (Flu)", new String[]{"Flu Medicine X", "Flu Medicine Y"});
        medicineMap.put("COVID-19", new String[]{"COVID Medicine Alpha", "COVID Medicine Beta"});
        medicineMap.put("Malaria", new String[]{"Anti-Malarial Drug X", "Anti-Malarial Drug Y"});
        medicineMap.put("Diabetes", new String[]{"Insulin Injection", "Diabetes Pills"});
        medicineMap.put("Hypertension (High Blood Pressure)", new String[]{"Antihypertensive A", "Antihypertensive B"});
        medicineMap.put("Asthma", new String[]{"Inhaler A", "Inhaler B"});
        medicineMap.put("Arthritis", new String[]{"Pain Reliever A", "Anti-inflammatory B"});
        medicineMap.put("Osteoporosis", new String[]{"Bone Density Medication A", "Calcium Supplement B"});
        medicineMap.put("Chronic Obstructive Pulmonary Disease (COPD)", new String[]{"COPD Medication A", "COPD Medication B"});
        medicineMap.put("Tuberculosis (TB)", new String[]{"TB Drug A", "TB Drug B"});
        medicineMap.put("HIV/AIDS", new String[]{"Antiretroviral A", "Antiretroviral B"});
        medicineMap.put("Hepatitis B", new String[]{"Hepatitis B Drug A", "Hepatitis B Drug B"});
        medicineMap.put("Hepatitis C", new String[]{"Hepatitis C Drug A", "Hepatitis C Drug B"});
        medicineMap.put("Dengue Fever", new String[]{"Pain Reliever A", "Fluid Replacement B"});
        medicineMap.put("Chikungunya", new String[]{"Pain Reliever A", "Anti-inflammatory B"});
        medicineMap.put("Zika Virus", new String[]{"Pain Reliever A", "Fluid Replacement B"});
        medicineMap.put("Ebola Virus Disease", new String[]{"Experimental Drug A", "Supportive Care B"});
        medicineMap.put("Rabies", new String[]{"Rabies Vaccine", "Rabies Immunoglobulin"});
        medicineMap.put("Measles", new String[]{"Vitamin A", "Fever Reducer B"});
        medicineMap.put("Chickenpox", new String[]{"Antiviral A", "Itch Reliever B"});
        medicineMap.put("Rubella (German Measles)", new String[]{"Fever Reducer A", "Pain Reliever B"});
        medicineMap.put("Mumps", new String[]{"Pain Reliever A", "Fever Reducer B"});
        medicineMap.put("Whooping Cough (Pertussis)", new String[]{"Antibiotic A", "Cough Suppressant B"});
        medicineMap.put("Polio (Poliomyelitis)", new String[]{"Polio Vaccine", "Supportive Care B"});
        medicineMap.put("Rotavirus Infection", new String[]{"Oral Rehydration Solution", "Probiotic B"});
        medicineMap.put("Norovirus Infection", new String[]{"Oral Rehydration Solution", "Anti-nausea B"});
        medicineMap.put("Gastroenteritis", new String[]{"Oral Rehydration Solution", "Antidiarrheal B"});
        medicineMap.put("Peptic Ulcer Disease", new String[]{"Antacid A", "Proton Pump Inhibitor B"});
        medicineMap.put("Gastritis", new String[]{"Antacid A", "Proton Pump Inhibitor B"});
        medicineMap.put("Irritable Bowel Syndrome (IBS)", new String[]{"Antispasmodic A", "Fiber Supplement B"});
        medicineMap.put("Crohn's Disease", new String[]{"Anti-inflammatory A", "Immunosuppressant B"});
        medicineMap.put("Ulcerative Colitis", new String[]{"Anti-inflammatory A", "Immunosuppressant B"});
        medicineMap.put("Appendicitis", new String[]{"Antibiotic A", "Pain Reliever B"});
        medicineMap.put("Gallstones", new String[]{"Pain Reliever A", "Ursodeoxycholic Acid B"});
        medicineMap.put("Kidney Stones", new String[]{"Pain Reliever A", "Alpha Blocker B"});
        medicineMap.put("Urinary Tract Infection (UTI)", new String[]{"Antibiotic A", "Pain Reliever B"});
        medicineMap.put("Prostatitis", new String[]{"Antibiotic A", "Alpha Blocker B"});
        medicineMap.put("Benign Prostatic Hyperplasia (BPH)", new String[]{"Alpha Blocker A", "5-Alpha Reductase Inhibitor B"});
        medicineMap.put("Erectile Dysfunction", new String[]{"Phosphodiesterase Inhibitor A", "Testosterone Replacement B"});
        medicineMap.put("Breast Cancer", new String[]{"Chemotherapy A", "Hormone Therapy B"});
        medicineMap.put("Lung Cancer", new String[]{"Chemotherapy A", "Targeted Therapy B"});
        medicineMap.put("Colorectal Cancer", new String[]{"Chemotherapy A", "Targeted Therapy B"});
        medicineMap.put("Prostate Cancer", new String[]{"Hormone Therapy A", "Chemotherapy B"});
        medicineMap.put("Skin Cancer (Melanoma, Basal Cell Carcinoma, Squamous Cell Carcinoma)", new String[]{"Surgery A", "Immunotherapy B"});
        medicineMap.put("Leukemia", new String[]{"Chemotherapy A", "Targeted Therapy B"});
        medicineMap.put("Lymphoma", new String[]{"Chemotherapy A", "Radiation Therapy B"});
        medicineMap.put("Ovarian Cancer", new String[]{"Chemotherapy A", "Targeted Therapy B"});
        medicineMap.put("Cervical Cancer", new String[]{"Radiation Therapy A", "Chemotherapy B"});
        medicineMap.put("Uterine Cancer", new String[]{"Hormone Therapy A", "Chemotherapy B"});
        medicineMap.put("Endometriosis", new String[]{"Hormonal Therapy A", "Pain Reliever B"});
        medicineMap.put("Polycystic Ovary Syndrome (PCOS)", new String[]{"Hormonal Therapy A", "Metformin B"});
        medicineMap.put("Premature Ejaculation (PE)", new String[]{"Antidepressant A", "Topical Anesthetic B"});
        medicineMap.put("Depression", new String[]{"Antidepressant A", "Psychotherapy B"});
        medicineMap.put("Anxiety Disorders", new String[]{"Antianxiety Medication A", "Cognitive Behavioral Therapy B"});
        medicineMap.put("Bipolar Disorder", new String[]{"Mood Stabilizer A", "Antipsychotic B"});
        medicineMap.put("Schizophrenia", new String[]{"Antipsychotic A", "Psychotherapy B"});
        medicineMap.put("Obsessive-Compulsive Disorder (OCD)", new String[]{"Antidepressant A", "Cognitive Behavioral Therapy B"});
        medicineMap.put("Attention-Deficit/Hyperactivity Disorder (ADHD)", new String[]{"Stimulant A", "Behavioral Therapy B"});
        medicineMap.put("Alzheimer's Disease", new String[]{"Cholinesterase Inhibitor A", "Memantine B"});
        medicineMap.put("Parkinson's Disease", new String[]{"Dopamine Agonist A", "MAO-B Inhibitor B"});
        medicineMap.put("Multiple Sclerosis (MS)", new String[]{"Disease-Modifying Therapy A", "Corticosteroid B"});
        medicineMap.put("Epilepsy", new String[]{"Anticonvulsant A", "Vagus Nerve Stimulation B"});
        medicineMap.put("Stroke", new String[]{"Thrombolytic A", "Antiplatelet B"});
        medicineMap.put("Myocardial Infarction (Heart Attack)", new String[]{"Aspirin", "Beta Blocker B"});
        medicineMap.put("Heart Failure", new String[]{"ACE Inhibitor A", "Diuretic B"});
        medicineMap.put("Hypertensive Heart Disease", new String[]{"Antihypertensive A", "Beta Blocker B"});
        medicineMap.put("Atherosclerosis", new String[]{"Statin A", "Antiplatelet B"});
        medicineMap.put("Peripheral Artery Disease (PAD)", new String[]{"Antiplatelet A", "Statin B"});
        medicineMap.put("Deep Vein Thrombosis (DVT)", new String[]{"Anticoagulant A", "Compression Stocking B"});
        medicineMap.put("Pulmonary Embolism (PE)", new String[]{"Anticoagulant A", "Thrombolytic B"});
        medicineMap.put("Sickle Cell Disease", new String[]{"Hydroxyurea A", "Pain Reliever B"});
        medicineMap.put("Thalassemia", new String[]{"Blood Transfusion", "Iron Chelation Therapy"});
        medicineMap.put("Hemophilia", new String[]{"Factor Replacement Therapy", "Desmopressin"});
        medicineMap.put("Anemia", new String[]{"Iron Supplement A", "Vitamin B12 B"});
        medicineMap.put("Cirrhosis", new String[]{"Diuretic A", "Lactulose B"});
        medicineMap.put("Nonalcoholic Fatty Liver Disease (NAFLD)", new String[]{"Vitamin E", "Pioglitazone"});
        medicineMap.put("Pancreatitis", new String[]{"Pain Reliever A", "Enzyme Replacement B"});
        medicineMap.put("Hypothyroidism", new String[]{"Levothyroxine"});
        medicineMap.put("Hyperthyroidism", new String[]{"Antithyroid Medication A", "Beta Blocker B"});
        medicineMap.put("Addison's Disease", new String[]{"Corticosteroid A", "Fludrocortisone"});
        medicineMap.put("Cushing's Syndrome", new String[]{"Adrenal Inhibitor A", "Surgery"});
        medicineMap.put("Fibromyalgia", new String[]{"Pain Reliever A", "Antidepressant B"});
        medicineMap.put("Chronic Fatigue Syndrome (CFS)", new String[]{"Pain Reliever A", "Antidepressant B"});
        medicineMap.put("Gout", new String[]{"Urate-Lowering Therapy A", "Anti-inflammatory B"});
        medicineMap.put("Rheumatoid Arthritis", new String[]{"Disease-Modifying Antirheumatic Drug A", "Biologic B"});
        medicineMap.put("Psoriasis", new String[]{"Topical Corticosteroid A", "Biologic B"});
        medicineMap.put("Eczema", new String[]{"Topical Steroid A", "Moisturizer B"});
        medicineMap.put("Vitiligo", new String[]{"Topical Steroid A", "Phototherapy B"});
        medicineMap.put("Allergies (Seasonal Allergic Rhinitis, Food Allergies)", new String[]{"Antihistamine A", "Decongestant B"});
        medicineMap.put("Chronic Sinusitis", new String[]{"Nasal Steroid A", "Antibiotic B"});
        medicineMap.put("Pneumonia", new String[]{"Antibiotic A", "Antiviral B"});
        medicineMap.put("Bronchitis", new String[]{"Cough Suppressant A", "Antibiotic B"});
        medicineMap.put("Fibroid Tumors (Uterine Fibroids)", new String[]{"GnRH Agonist A", "Uterine Artery Embolization"});
        medicineMap.put("Endometrial Polyps", new String[]{"Hormonal Therapy A", "Surgical Removal"});
        medicineMap.put("Pelvic Inflammatory Disease (PID)", new String[]{"Antibiotic A", "Pain Reliever B"});
        medicineMap.put("Menstrual Disorders (Dysmenorrhea, Menorrhagia)", new String[]{"NSAID A", "Oral Contraceptive B"});
        medicineMap.put("Infertility", new String[]{"Fertility Drug A", "Hormone Therapy B"});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == diseasesButton) {
            JPanel checkboxPanel = new JPanel();
            checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
            for (JCheckBox checkBox : diseaseCheckBoxes) {
                checkboxPanel.add(checkBox);
            }
            JScrollPane scrollPane = new JScrollPane(checkboxPanel);
            scrollPane.setPreferredSize(new Dimension(300, 400));
            int option = JOptionPane.showConfirmDialog(this, scrollPane, "Select Diseases", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                StringBuilder selectedMedicines = new StringBuilder("Selected Medicines:\n");
                for (JCheckBox checkBox : diseaseCheckBoxes) {
                    if (checkBox.isSelected()) {
                        String disease = checkBox.getText();
                        String[] medicines = medicineMap.get(disease);
                        if (medicines != null) {
                            selectedMedicines.append(disease).append(":\n");
                            for (String medicine : medicines) {
                                selectedMedicines.append("  - ").append(medicine).append("\n");
                            }
                        }
                    }
                }
                JTextArea textArea = new JTextArea(selectedMedicines.toString());
                textArea.setEditable(false);
                JScrollPane medicineScrollPane = new JScrollPane(textArea);
                medicineScrollPane.setPreferredSize(new Dimension(400, 300));
                JOptionPane.showMessageDialog(this, medicineScrollPane, "Medicines for Selected Diseases", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryFrame());
    }
}