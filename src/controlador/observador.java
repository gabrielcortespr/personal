package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.personal;
import vista.vista_principal;
import vista.vista_agregar_empleado;
import vista.vista_listar;
import vista.vista_eliminar;
import vista.vista_modificar;
import vista.vista_acciones;

public class observador implements ActionListener, MouseListener {

    private vista_principal vista_inicio;
    private vista_agregar_empleado vista_agregar = new vista_agregar_empleado();
    private vista_listar vista_listar = new vista_listar();
    private vista_eliminar vista_eliminar = new vista_eliminar();
    private vista_modificar vista_modificar = new vista_modificar();
    private vista_acciones vista_acciones = new vista_acciones();

    private personal perso = new personal();

    public enum Accion {
        btn_agregar,
        menu_agregar,
        menu_listar,
        menu_eliminar,
        menu_modificar,
        menu_version,
        btnEliminarEmp,
        btnBuscarEmp,
        btnModificar
    }

    /**
     * Constructor de clase
     */
    public observador(JFrame padre) {
        this.vista_inicio = (vista_principal) padre;
    }

    /**
     * Inicia todos las acciones y listener de la vista
     */
    public void iniciar() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista_inicio);
            SwingUtilities.updateComponentTreeUI(this.vista_agregar);
            SwingUtilities.updateComponentTreeUI(this.vista_listar);
            SwingUtilities.updateComponentTreeUI(this.vista_eliminar);
            SwingUtilities.updateComponentTreeUI(this.vista_modificar);
            SwingUtilities.updateComponentTreeUI(this.vista_acciones);
            this.vista_inicio.setLocationRelativeTo(null);
            this.vista_inicio.setTitle("Base Datos empleados");
            this.vista_inicio.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }

        //Escuchamos los botones de todas partes
        this.vista_inicio.menu_agregar.setActionCommand("menu_agregar");
        this.vista_inicio.menu_agregar.addActionListener(this);
        this.vista_inicio.menu_listar.setActionCommand("menu_listar");
        this.vista_inicio.menu_listar.addActionListener(this);
        this.vista_inicio.menu_eliminar.setActionCommand("menu_eliminar");
        this.vista_inicio.menu_eliminar.addActionListener(this);
        this.vista_inicio.menu_modificar.setActionCommand("menu_modificar");
        this.vista_inicio.menu_modificar.addActionListener(this);
        this.vista_inicio.menu_version.setActionCommand("menu_version");
        this.vista_inicio.menu_version.addActionListener(this);

        this.vista_agregar.btn_agregar.setActionCommand("btn_agregar");
        this.vista_agregar.btn_agregar.addActionListener(this);
        
        this.vista_eliminar.btn_elim.setActionCommand("btnEliminarEmp");
        this.vista_eliminar.btn_elim.addActionListener(this);
        
        this.vista_modificar.btn_buscar.setActionCommand("btnBuscarEmp");
        this.vista_modificar.btn_buscar.addActionListener(this);
        this.vista_modificar.btn_modificar.setActionCommand("btnModificar");
        this.vista_modificar.btn_modificar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Accion.valueOf(e.getActionCommand())) {
            case menu_agregar:
                this.vista_agregar.setLocationRelativeTo(null);
                this.vista_agregar.setTitle("Agregar Empleado");
                this.vista_agregar.setVisible(true);
                this.vista_inicio.setVisible(false);
                break;
            case menu_listar:
                this.vista_listar.setLocationRelativeTo(null);
                this.vista_listar.setTitle("Listar Empleado");
                this.vista_listar.setVisible(true);
                this.vista_inicio.setVisible(false);
                this.vista_listar.jt_listar.setModel(this.perso.ListadoEmp());
                break;
            case menu_eliminar:
                this.vista_eliminar.setLocationRelativeTo(null);
                this.vista_eliminar.setTitle("Eliminar Empleado");
                this.vista_eliminar.setVisible(true);
                this.vista_inicio.setVisible(false);
                break;
            case menu_modificar:
                this.vista_modificar.setLocationRelativeTo(null);
                this.vista_modificar.setTitle("Modificar Empleado");
                this.vista_modificar.setVisible(true);
                this.vista_inicio.setVisible(false);
                break;
            case menu_version:
                this.vista_acciones.setLocationRelativeTo(null);
                this.vista_acciones.setTitle("Acciones GIT");
                this.vista_acciones.setVisible(true);
                this.vista_inicio.setVisible(false);
                break;
            case btn_agregar:
                //Enviamos datos del formulario Ingresar producto a metodo nuevoProducto
                if (Integer.parseInt(this.vista_agregar.tf_cod.getText()) > 0 && Integer.parseInt(this.vista_agregar.tf_cod.getText()) <= 100) {
                    if (this.vista_agregar.tf_rut.getText().length() > 0) {
                        if (this.vista_agregar.tf_nom.getText().length() > 0) {
                            if (this.vista_agregar.tf_apellido.getText().length() > 0) {
                                if (this.vista_agregar.tf_cel.getText().length() == 9) {
                                    if (Integer.parseInt(this.vista_agregar.tf_sueldo.getText()) >= 120000) {
                                        if (this.perso.buscarEmp(Integer.parseInt(this.vista_agregar.tf_cod.getText()))) {
                                            JOptionPane.showMessageDialog(null, "El código de empleado ya existe!");
                                        } else {
                                            if (this.perso.guardarEmp(Integer.parseInt(this.vista_agregar.tf_cod.getText()),
                                                    this.vista_agregar.tf_rut.getText(),
                                                    this.vista_agregar.tf_nom.getText(),
                                                    this.vista_agregar.tf_apellido.getText(),
                                                    this.vista_agregar.tf_cel.getText(),
                                                    this.vista_agregar.tf_email.getText(),
                                                    this.vista_agregar.tf_sueldo.getText(),
                                                    this.vista_agregar.cb_est_civil.getSelectedItem().toString(),
                                                    this.vista_agregar.cb_depto.getSelectedItem().toString()
                                            )) {
                                                JOptionPane.showMessageDialog(null, "Empleado agregada correctamente");
                                                //Limpiamos textField
                                                this.vista_agregar.tf_cod.setText("");
                                                this.vista_agregar.tf_nom.setText("");
                                                this.vista_agregar.tf_rut.setText("");
                                                this.vista_agregar.tf_apellido.setText("");
                                                this.vista_agregar.tf_email.setText("");
                                                this.vista_agregar.tf_cel.setText("");
                                                this.vista_agregar.tf_sueldo.setText("");
                                            } else {
                                                JOptionPane.showMessageDialog(null, "No se pudo agregar el empleado");
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El SUELDO debe ser mayor o igual a $120000!");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El CELULAR debe ser de 9 caractéres!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El APELLIDO no debe estar vacío!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El NOMBRE no debe estar vacío!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El RUT no debe estar vacío!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El código debe ser entre 1 y 100!");
                }
                break;
            case btnEliminarEmp:
                if (this.perso.buscarEmp(Integer.parseInt(this.vista_eliminar.tf_elim.getText()))) {
                    if (this.perso.eliminarEmp(Integer.parseInt(this.vista_eliminar.tf_elim.getText()))) {
                        JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente");
                        this.vista_eliminar.tf_elim.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar el empleado");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El código ingresado no existe!");
                }
                break;
            case btnBuscarEmp:
                int code = Integer.parseInt(this.vista_modificar.tf_buscar.getText());
                if (this.perso.buscarEmp(code)) {
                    String rut = "";
                    String nom = "";
                    String ape = "";
                    String cel = "";
                    String email = "";
                    String sueldo = "";
                    String est_civ = "";
                    String depto = "";
                    
                    rut = this.perso.buscarPorCodigo(code).get(0).getRut();
                    nom = this.perso.buscarPorCodigo(code).get(0).getNombre();
                    ape = this.perso.buscarPorCodigo(code).get(0).getApellido();
                    cel = this.perso.buscarPorCodigo(code).get(0).getCelular();
                    email = this.perso.buscarPorCodigo(code).get(0).getEmail();
                    sueldo = this.perso.buscarPorCodigo(code).get(0).getSueldo_bruto();
                    est_civ = this.perso.buscarPorCodigo(code).get(0).getEst_civil();
                    depto = this.perso.buscarPorCodigo(code).get(0).getNom_depto();

                    this.vista_modificar.tf_rut.setText(rut);
                    this.vista_modificar.tf_nom.setText(nom);
                    this.vista_modificar.tf_apell.setText(ape);
                    this.vista_modificar.tf_cel.setText(cel);
                    this.vista_modificar.tf_email.setText(email);
                    this.vista_modificar.tf_sueldo.setText(sueldo);
                    this.vista_modificar.cb_est_civ.setSelectedItem(est_civ);
                    this.vista_modificar.cb_depto.setSelectedItem(depto);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un registro!");
                }

                break;
            case btnModificar:
                if (this.perso.modificaEmpleado(
                        Integer.parseInt(this.vista_modificar.tf_buscar.getText()),
                        this.vista_modificar.tf_rut.getText(),
                        this.vista_modificar.tf_nom.getText(),
                        this.vista_modificar.tf_apell.getText(),
                        this.vista_modificar.tf_cel.getText(),
                        this.vista_modificar.tf_email.getText(),
                        this.vista_modificar.tf_sueldo.getText(),
                        this.vista_modificar.cb_est_civ.getSelectedItem().toString(),
                        this.vista_modificar.cb_depto.getSelectedItem().toString()
                        )
                   ) {
                    JOptionPane.showMessageDialog(null, "Empleado actualizado");
                }
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1)//boton izquierdo
        {
            //Muestro datos de producto a modificar
            /*int fila = this.listProd.tbProducto.rowAtPoint(e.getPoint());
             if (fila > -1){
                this.listProd.txtnombre.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 2) ));
                this.listProd.txtfamprod.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 3) ));
                this.listProd.txtdescripcion.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 6) ));
                this.listProd.txtcantidad.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 8) ));
                this.listProd.txtprecio.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 7) ));
                this.listProd.txtcod.setText(String.valueOf(this.listProd.tbProducto.getValueAt(fila, 1) ));
             }*/
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
