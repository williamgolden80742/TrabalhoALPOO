/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.DAO.alunosDAO;
import Model.DAO.cursosDAO;
import Model.DAO.disciplinasDAO;
import Model.bean.Aluno;
import Model.bean.Curso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class editarCriarAluno extends javax.swing.JFrame implements ActionListener {

    private Aluno aluno = new Aluno();
    private boolean selectedAluno = false;
    private alunosDAO aDAO = new alunosDAO();
    private cursosDAO cDAO = new cursosDAO();
    private List<Curso> cList = cDAO.read();

    public editarCriarAluno() {
        initComponents();
        addGroup();
        setSaveButton();
        setLocation(400, 200);
        setCursosSelectedList();
        notasButton.setVisible(false);
    }

    void setCursosSelectedList() {
        cList.forEach((p) -> {
            cursoComboBox.addItem(p.getCodCurso() + " - " + p.getNomeCurso() + " - " + p.getTipoCurso());
        });
    }

    private boolean isSelectedAluno() {
        return selectedAluno;
    }

    private void setSelectedAluno() {
        this.selectedAluno = true;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        nomeAluno.setText(aluno.getNomeAluno());
        dataNasc.setText(aluno.getDataNascAluno());
        int i = 0;
        for (Curso c : cList) {
            if (c.getCodCurso() == aluno.getCodCurso()) {
                cursoComboBox.setSelectedIndex(i + 1);
            }
            i++;
        }
        notasButton.setVisible(true);
        setSelectedAluno();
        setSaveButton();
    }

    void addGroup() {
        salvarButton.addActionListener(this);
        cancelarButton.addActionListener(this);
        notasButton.addActionListener(this);
        //  --------------------------------------------------------             
    }

    void setSaveButton() {
        if (isSelectedAluno() == true) {
            salvarButton.setText("SALVAR");
        } else {
            salvarButton.setText("CRIAR");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salvarButton) {
            Salvar();
        }
        if (e.getSource() == notasButton) {
            alunoMedia aMv = new alunoMedia(aDAO.readNotas(aluno.getMatricula()));
            aMv.setVisible(true);
        }
        if (e.getSource() == cancelarButton) {
            dispose();
        }
    }

    void Salvar() {
        Aluno a = new Aluno();
        boolean b = true;
        if (!nomeAluno.getText().equals("")) {
            a.setNomeAluno(nomeAluno.getText());
        } else {
            b = false;
            JOptionPane.showMessageDialog(null, "Digite nome de aluno");
        }
        a.setDataNascAluno(dataNasc.getText());
        try {
            a.setCodCurso(cList.get(cursoComboBox.getSelectedIndex() - 1).getCodCurso());
        } catch (Exception ex) {
            b = false;
            JOptionPane.showMessageDialog(null, "selecione o curso");
        }
        if (b) {
            if (isSelectedAluno()) {
                a.setMatricula(aluno.getMatricula());
                aDAO.update(a);
            } else {
                aDAO.create(a);
            }
            statusCurso.setText(aDAO.getAlunosStatus());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoCursoGroup = new javax.swing.ButtonGroup();
        salvarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        aulaLabel1 = new javax.swing.JLabel();
        cursoComboBox = new javax.swing.JComboBox<>();
        statusCurso = new javax.swing.JLabel();
        nomeAluno = new javax.swing.JTextField();
        dataNasc = new javax.swing.JFormattedTextField();
        aulaLabel2 = new javax.swing.JLabel();
        notasButton = new javax.swing.JButton();

        setTitle("Aluno");

        salvarButton.setText("SALVAR");

        cancelarButton.setText("CANCELAR");

        aulaLabel1.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        aulaLabel1.setText("Nome : ");

        cursoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "selecione curso" }));

        statusCurso.setBackground(new java.awt.Color(51, 153, 0));
        statusCurso.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        statusCurso.setForeground(new java.awt.Color(51, 153, 0));
        statusCurso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        try {
            dataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        aulaLabel2.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        aulaLabel2.setText("Nasc. :");

        notasButton.setText("NOTAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(aulaLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aulaLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dataNasc, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cursoComboBox, 0, 160, Short.MAX_VALUE))
                    .addComponent(nomeAluno))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(statusCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(salvarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(notasButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aulaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aulaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cursoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notasButton)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(statusCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salvarButton)
                    .addComponent(cancelarButton))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dataNasc, nomeAluno});

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aulaLabel1;
    private javax.swing.JLabel aulaLabel2;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox<String> cursoComboBox;
    private javax.swing.JFormattedTextField dataNasc;
    private javax.swing.JTextField nomeAluno;
    private javax.swing.JButton notasButton;
    private javax.swing.JButton salvarButton;
    private javax.swing.JLabel statusCurso;
    private javax.swing.ButtonGroup tipoCursoGroup;
    // End of variables declaration//GEN-END:variables

}
