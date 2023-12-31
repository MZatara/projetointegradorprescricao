/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.senac.academiajpa2023.view;

import br.com.senac.academiajpa2023.persistence.ExerciciosDAO;
import br.com.senac.academiajpa2023.persistence.JPAUtil;
import br.com.senac.academiajpa2023.ucpi2atv5.Exercicios;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author MPSantos
 */
public class Listagem extends javax.swing.JFrame {

    private int id_permissao;

    /**
     * Creates new form Listagem
     */
        public Listagem() {
          initComponents();
        ExerciciosDAO exerciciosdao = new ExerciciosDAO();
        List<Exercicios> exercicios = exerciciosdao.Carregar();
        preencherTabela();
    }
    
      public void validaPermissao(int id) {
        this.id_permissao = id;

        if (id_permissao == 2) {
            jfbCadastrar2.setVisible(false);
            jfbExcluir1.setVisible(false);
        } 
    }
       private Login login;

    public void preencherTabela() {

        EntityManager em = JPAUtil.getEntityManager();

        Query consulta = em.createQuery("select exe from Exercicios exe ");
        List<Exercicios> lista = consulta.getResultList();

        DefaultTableModel listagem = (DefaultTableModel) table1.getModel();

        table1.setRowSorter(new TableRowSorter(listagem));

        listagem.setNumRows(0);

        for (Exercicios e : lista) {

            Object[] obj = new Object[]{
                e.getId(),
                e.getNome_exercicio(),
                e.getSeries(),
                e.getRepeticoes()                
            };
            listagem.addRow(obj);

        }

    }
      public void preencherTabela2() {

        ExerciciosDAO exerciciosdao = new ExerciciosDAO();

        String nome_exercicio = jftPesquisar1.getText();

        List<Exercicios> exercicios = exerciciosdao.listar(nome_exercicio);

        DefaultTableModel listagem = (DefaultTableModel) table1.getModel();

        table1.setRowSorter(new TableRowSorter(listagem));

        listagem.setNumRows(0);

        for (Exercicios e : exercicios) {

            Object[] obj = new Object[]{
                e.getId(),
                e.getNome_exercicio(),
                e.getSeries(),
                e.getRepeticoes()                
            };

            listagem.addRow(obj);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jftPesquisar1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jfbCadastrar2 = new javax.swing.JButton();
        jfbExcluir1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Academia");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Lista de Exercícios");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Pesquisar Exercício:");

        jftPesquisar1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jftPesquisar1CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jftPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(153, 153, 153))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(130, 130, 130))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jftPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Exercício", "Séries", "Repetições"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        jfbCadastrar2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jfbCadastrar2.setText("Cadastrar");
        jfbCadastrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfbCadastrar2ActionPerformed(evt);
            }
        });

        jfbExcluir1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jfbExcluir1.setText("Excluir");
        jfbExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfbExcluir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jfbCadastrar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jfbExcluir1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jfbCadastrar2)
                    .addComponent(jfbExcluir1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jfbCadastrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfbCadastrar2ActionPerformed
        // TODO add your handling code here:
            Cadastro telaCadastrar = new Cadastro();
        telaCadastrar.setVisible(true);
        dispose();
    }//GEN-LAST:event_jfbCadastrar2ActionPerformed

    private void jfbExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfbExcluir1ActionPerformed
        // TODO add your handling code here:
         try {
            if (table1.getSelectedRow() >= 0) {

                int id = (Integer) table1.getValueAt(table1.getSelectedRow(), 0);

                int resposta = JOptionPane.showConfirmDialog(this, "Deseja mesmo excluir o registro " + id + "?");
                if (resposta == 0) {

                    ExerciciosDAO exedao = new ExerciciosDAO();
                    exedao.excluir(id);

                    preencherTabela();
                    JOptionPane.showMessageDialog(this, "Registro excluído com sucesso");

                }
            }
        } catch (Exception p) {
            JOptionPane.showMessageDialog(this, "Ocorreu uma falha:\n" + p.getMessage());
        }
    }//GEN-LAST:event_jfbExcluir1ActionPerformed

    private void jftPesquisar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jftPesquisar1CaretUpdate
        // TODO add your handling code here:
            String nome = jftPesquisar1.getText();

        if (nome.isEmpty()) {
            preencherTabela();
        } else {
            preencherTabela2();
        }
    }//GEN-LAST:event_jftPesquisar1CaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Listagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Listagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Listagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Listagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Listagem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jfbCadastrar2;
    private javax.swing.JButton jfbExcluir1;
    private javax.swing.JTextField jftPesquisar1;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
