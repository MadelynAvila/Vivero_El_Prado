/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RegistroProductos;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
//Librerias para reporte pdf 
import java.util.logging.Level;
import java.util.logging.Logger;
import ConexionProductos.Conexion;
 import static javax. swing.WindowConstants.DISPOSE_ON_CLOSE;
 import net.sf.jasperreports.engine.JRException;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import net.sf.jasperreports.engine.util.JRLoader;
 import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author madel
 */
public class Productoss extends javax.swing.JFrame {

    /**
     * Creates new form Productoss
     */
    public Productoss() {
        initComponents();
        mostrarDatos();
        //Comentario de prueba
    }
    public void mostrarDatos(){
        String[] titulos = {"Codigo Producto ","Nombre Producto", "Precio Unitario","Cantidad Producto", "FechadeIngreso"};
        String[] Registros = new String[5];

        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        String SQL = "SELECT * FROM producto";

        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                Registros[0]=rs.getString(1);
                Registros[1]=rs.getString(2);
                Registros[2]=rs.getString(3);
                Registros[3]=rs.getString(4);
                Registros[4]=rs.getString(5);
                
                modelo.addRow(Registros);
            }
            tbproducto.setModel(modelo);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error Al Mostrar Datos "+e.getMessage());
        }
    }
    public void insertarDatos(){
        try{
            String SQL = "INSERT INTO producto (codigoProducto,nombreProducto,precioUnitario,cantidadProducto,FechadeIngreso) VALUES(?,?,?,?,?)";
            
            PreparedStatement pst = cn.prepareStatement(SQL);
            
            pst.setString(1, txtproducto.getText());
            pst.setString(2, txtnombre.getText());
            pst.setString(3, txtprecio.getText());
            pst.setString(4, txtexistencia.getText());
            pst.setString(5, txtfecha.getText());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Registro Exitoso");

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error de Registros  "+e.getMessage());
        }
    }
     //Metodo de busqueda de productos por nombre y codigo
      public void buscar(){
         // Obtenemos el valor de la caja de búsqueda
            String madelyn = CajaBusqueda.getText();
            // Validamos su contenido
            if (madelyn.isEmpty()) { // O puedes usar: if (madelyn.equals(""))
                JOptionPane.showMessageDialog(null, "Ingrese un valor a buscar ");
                return;
            }
          //like '%busqueda%'
              String SQL = "SELECT * FROM producto where nombreProducto like '%"+madelyn+"%' or codigoProducto like '%"+madelyn+"%' ";

              String[] titulos = {"Codigo Producto ","Nombre Producto", "Precio Unitario","Cantidad Producto", "FechadeIngreso"};
              String[] Registros = new String[5];

              DefaultTableModel modelo = new DefaultTableModel(null, titulos);

              try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);

          boolean  MadelynExiste = false;

            while(rs.next()){
                MadelynExiste = true;
                Registros[0]=rs.getString(1);
                Registros[1]=rs.getString(2);
                Registros[2]=rs.getString(3);
                Registros[3]=rs.getString(4);
                Registros[4]=rs.getString(5);
                modelo.addRow(Registros);
            }
            if (MadelynExiste) 
            {
              tbproducto.setModel(modelo);
            }
            else{
              JOptionPane.showMessageDialog(null, "No hay resultado");
            }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error Al Mostrar Datos "+e.getMessage());
        }
    }
    
    public void limpiarDatos(){
        txtproducto.setText("");
        txtnombre.setText("");
        txtprecio.setText("");
        txtexistencia.setText("");
        txtfecha.setText("");
    }
    public void eliminarDatos(){
        int filaSeleccionada = tbproducto.getSelectedRow();
        try{
            String SQL = "DELETE FROM producto WHERE codigoProducto="+tbproducto.getValueAt(filaSeleccionada, 0);
            
            Statement st = cn.createStatement();
            int n = st.executeUpdate(SQL);
            
            if(n>=0){
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error Al Eliminar El Registro"+e.getMessage());
        }
    }
    public void actualizarDatos(){
        try{
            String SQL = "UPDATE producto SET nombreProducto=?,precioUnitario=?, cantidadProducto=?, FechadeIngreso=? WHERE codigoProducto=? ";
            
            int filaSeleccionada = tbproducto.getSelectedRow();
            String dao=(String)tbproducto.getValueAt(filaSeleccionada, 0);
            PreparedStatement  pst=cn.prepareStatement(SQL);

            pst.setString(1, txtnombre.getText());
            pst.setString(2, txtprecio.getText());
            pst.setString(3, txtexistencia.getText());
            pst.setString(4, txtfecha.getText());
            pst.setString(5, dao);
            pst.execute();
            
            JOptionPane.showMessageDialog(null,"Registro Actualizado");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Registro No Actualizado"+e.getMessage());
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
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtproducto = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtprecio = new javax.swing.JTextField();
        txtexistencia = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        CajaBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbproducto = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("Vivero El Prado ");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/FlorrGrande.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(164, 216, 164));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos:"));

        jLabel2.setText("Codigo Del Producto");

        jLabel3.setText("Nombre Producto");

        jLabel4.setText("Precio Unitario");

        jLabel5.setText("Existencia ");

        jLabel6.setText("Fecha de Ingreso");

        txtproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductoActionPerformed(evt);
            }
        });

        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });

        txtprecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprecioActionPerformed(evt);
            }
        });

        txtexistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtexistenciaActionPerformed(evt);
            }
        });

        txtfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(58, 58, 58)))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprecio)
                            .addComponent(txtproducto)
                            .addComponent(txtexistencia)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(81, 81, 81)
                        .addComponent(txtnombre))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(81, 81, 81)
                        .addComponent(txtfecha)))
                .addGap(57, 57, 57))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtexistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel3.setBackground(new java.awt.Color(142, 204, 142));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Propiedades"));

        jButton1.setBackground(new java.awt.Color(85, 135, 105));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Guardarrrrr.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(85, 135, 105));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Eliminarrrrr.png"))); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(85, 135, 105));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Actualizar.png"))); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(85, 135, 105));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Limpiarrrrr2.png"))); // NOI18N
        jButton4.setText("Limpiar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        CajaBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CajaBusquedaActionPerformed(evt);
            }
        });

        tbproducto.setBackground(new java.awt.Color(158, 204, 144));
        tbproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbproducto);

        jButton5.setBackground(new java.awt.Color(85, 135, 105));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BuscarEstesi.png"))); // NOI18N
        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(85, 135, 105));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Refrescardatos.png"))); // NOI18N
        jButton6.setText("Recargar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Generar Repote.png"))); // NOI18N
        jButton7.setText("Reporte");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(30, 30, 30)))
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CajaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5)
                            .addComponent(jButton6)
                            .addComponent(jButton7)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CajaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductoActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprecioActionPerformed
        // TODO add your handling code here:
        //jhvhg
    }//GEN-LAST:event_txtprecioActionPerformed

    private void txtexistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtexistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtexistenciaActionPerformed

    private void txtfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaActionPerformed

    private void tbproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductoMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = tbproducto.rowAtPoint(evt.getPoint());
        txtproducto.setText(tbproducto.getValueAt(filaSeleccionada, 0).toString());
        txtnombre.setText(tbproducto.getValueAt(filaSeleccionada, 1).toString());
        txtprecio.setText(tbproducto.getValueAt(filaSeleccionada, 2).toString());
        txtexistencia.setText(tbproducto.getValueAt(filaSeleccionada, 3).toString());
        txtfecha.setText(tbproducto.getValueAt(filaSeleccionada, 4).toString());
    }//GEN-LAST:event_tbproductoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        insertarDatos();
        mostrarDatos();
        limpiarDatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        eliminarDatos();
        mostrarDatos();
        limpiarDatos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        actualizarDatos();
        mostrarDatos();
        limpiarDatos(); 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        limpiarDatos();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        mostrarDatos();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void CajaBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CajaBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CajaBusquedaActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
      Conexion cc = new Conexion();
    Connection cn = cc.Conexion();
    
     JasperReport  reporte = null;
     String path = "src\\reporte\\Reporte1.jasper";
     reporte = (JasperReport)  JRLoader.loadObjectFromFile(path);
     JasperPrint  jprint = JasperFillManager.fillReport (reporte, null, cn);
     JasperViewer view = new JasperViewer (jprint, false);
    view.setDefaultCloseOperation (DISPOSE_ON_CLOSE);
    view.setVisible (true);
                                    
 } catch (JRException ex) {
    Logger.getLogger (Productoss.class.getName ()).log (Level. SEVERE, null, ex);
 }
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    
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
            java.util.logging.Logger.getLogger(Productoss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productoss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productoss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productoss.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productoss().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CajaBusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbproducto;
    private javax.swing.JTextField txtexistencia;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtproducto;
    // End of variables declaration//GEN-END:variables

Conexion cc = new Conexion();
    Connection cn = cc.Conexion();
}
