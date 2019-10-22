import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CadastroAluno extends JFrame {

	private JLabel labelInformacao, labelCodigo, labelNome, labelEmail, labelCidade, labelBairro, labelCurso,
			labelLogica, labelFoto;
	private JButton btnConcluir, btnConsultar, btnAlterar;
	private JTextField tfCodigo, tfNome, tfEmail, tfBairro;
	private JComboBox listaDasCidades, listaCursos;
	private JRadioButton RadioBtnSim, RadioBtnNao;
	private ButtonGroup grupo;
	private JPanel painelFoto;
	private JMenuItem itemMenuEditar, itemMenuEditar2;
	private JMenu menuEditar, menuArquivo, menuAjuda;
	private JMenuBar menuBarra;

	public static void main(String[] args) {
		CadastroAluno frame = new CadastroAluno();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public CadastroAluno() {
		inicializaComponentes();
		defineMetodos();
	}

	public void inicializaComponentes() {
		setTitle("Cadastro de Aluno");

		setBounds(100, 100, 450, 380);

		setLocationRelativeTo(null);

		setLayout(null);
		labelInformacao = new JLabel("Insira suas informacoes abaixo para se inscrever!");
		labelInformacao.setBounds(30, 20, 400, 20);

		labelCodigo = new JLabel("Codigo");
		labelCodigo.setBounds(10, 50, 100, 20);

		labelNome = new JLabel("Nome");
		labelNome.setBounds(10, 80, 100, 20);

		labelEmail = new JLabel("Email");
		labelEmail.setBounds(10, 110, 100, 20);

		labelCidade = new JLabel("Cidade");
		labelCidade.setBounds(10, 140, 100, 20);

		labelBairro = new JLabel("Bairro");
		labelBairro.setBounds(10, 170, 100, 20);

		labelCurso = new JLabel("Curso");
		labelCurso.setBounds(10, 200, 100, 20);

		labelLogica = new JLabel("Tem conhecimento em logica?");
		labelLogica.setBounds(10, 230, 220, 20);

		labelFoto = new JLabel("Foto");
		labelFoto.setBounds(350, 220, 80, 20);

		add(labelInformacao);
		add(labelCodigo);
		add(labelNome);
		add(labelEmail);
		add(labelCidade);
		add(labelBairro);
		add(labelCurso);
		add(labelLogica);
		add(labelFoto);

		RadioBtnSim = new JRadioButton("Sim");
		RadioBtnSim.setBounds(60, 250, 50, 20);

		RadioBtnNao = new JRadioButton("Nao");
		RadioBtnNao.setBounds(150, 250, 70, 20);

		grupo = new ButtonGroup();
		grupo.add(RadioBtnSim);
		grupo.add(RadioBtnNao);

		tfCodigo = new JTextField();
		tfCodigo.setBounds(100, 50, 70, 20);

		tfNome = new JTextField();
		tfNome.setBounds(100, 80, 170, 20);

		tfEmail = new JTextField();
		tfEmail.setBounds(100, 110, 170, 20);

		tfBairro = new JTextField();
		tfBairro.setBounds(100, 170, 170, 20);

		ArrayList<String> listaCidade = new ArrayList<>();
		listaCidade.add("Fortaleza");
		listaCidade.add("Aquiraz");
		listaCidade.add("Maracanau");
		listaCidade.add("Caucaia");
		listaCidade.add("Maranguape");
		listaCidade.add("Eusebio");
		Collections.sort(listaCidade);
		listaDasCidades = new JComboBox(new Vector<String>(listaCidade));
		listaDasCidades.setBounds(100, 140, 120, 20);
		listaDasCidades.setEditable(false);
		listaDasCidades.setSelectedItem(null);

		ArrayList<String> listaCurso = new ArrayList<>();
		listaCurso.add("");
		listaCurso.add("PHP");
		listaCurso.add("JAVA");
		listaCurso.add("PYTHON");
		listaCurso.add("RUBY");
		listaCurso.add("C#");
		Collections.sort(listaCurso);
		listaCursos = new JComboBox(new Vector<String>(listaCurso));
		listaCursos.setBounds(100, 200, 80, 20);
		listaCursos.setEditable(false);
		painelFoto = new JPanel();
		painelFoto.setBackground(Color.DARK_GRAY);
		painelFoto.setBounds(300, 70, 120, 145);
		btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(10, 280, 100, 20);
		btnConsultar = new JButton("Consulta");
		btnConsultar.setBounds(120, 280, 100, 20);
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(240, 280, 100, 20);
		itemMenuEditar = new JMenuItem("Limpar");
		menuArquivo = new JMenu("Arquivo");
		itemMenuEditar2 = new JMenuItem("Deletar");
		menuEditar = new JMenu("Editar");
		menuEditar.add(itemMenuEditar);
		menuEditar.add(itemMenuEditar2);
		menuAjuda = new JMenu("Ajuda");
		menuBarra = new JMenuBar();
		setJMenuBar(menuBarra);
		menuBarra.add(menuArquivo);
		menuBarra.add(menuEditar);
		menuBarra.add(menuAjuda);
		add(RadioBtnSim);
		add(RadioBtnNao);
		add(tfCodigo);
		add(tfNome);
		add(tfEmail);
		add(tfBairro);
		add(listaDasCidades);
		add(listaCursos);
		add(labelLogica);
		add(painelFoto);
		add(btnConcluir);
		add(btnConsultar);
		add(btnAlterar);
	}

	public String restore(String email) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		String sql = "SELECT * FROM `cadastroAluno` WHERE email =  '" + email + "'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			return rs.getString("id");
		}
		return rs.getString("id");
	}

	public void defineMetodos() {
		tfCodigo.setEnabled(false);
		btnAlterar.setEnabled(false);
		itemMenuEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tfCodigo.setText("");
				tfNome.setText("");
				tfEmail.setText("");
				tfBairro.setText("");
				listaDasCidades.setSelectedItem(null);
				listaCursos.setSelectedItem(null);
				grupo.clearSelection();

				btnAlterar.setEnabled(false);
			}
		});
		btnConcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent campoVazio) {
				Connection connection = new ConnectionFactory().getConnection();
				if (tfNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O Nome não pode estar em branco");
					tfNome.requestFocus();
				} else if (tfEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O Email não pode estar em branco");
					tfEmail.requestFocus();
				} else if (listaCursos.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha o curso que deseja fazer");
					listaCursos.requestFocus();
				} else {

					String sql = "insert into cadastroAluno" + "(nome,email,cidade,bairro,curso,knew)"
							+ " values (?,?,?,?,?,?)";
					try {
						PreparedStatement stmt = connection.prepareStatement(sql);
						stmt.setString(1, tfNome.getText());
						stmt.setString(2, tfEmail.getText());
						stmt.setString(3, (String) listaDasCidades.getSelectedItem());
						stmt.setString(4, tfBairro.getText());
						stmt.setString(5, (String) listaCursos.getSelectedItem());
						if (RadioBtnSim.isSelected()) {
							stmt.setString(6, RadioBtnSim.getText());
						}
						if (RadioBtnNao.isSelected()) {
							stmt.setString(6, RadioBtnNao.getText());
						}
						stmt.execute();
						stmt.close();

						String a = restore(tfEmail.getText());

						JOptionPane.showMessageDialog(null,
								"Arquivo Gravado !\nAluno(a) matriculado(a) com registro: " + a);

						tfCodigo.setText("");
						tfNome.setText("");
						tfEmail.setText("");
						listaDasCidades.setSelectedItem(null);
						tfBairro.setText("");
						listaCursos.setSelectedItem(null);
						grupo.clearSelection();

					} catch (Exception Erro) {
						JOptionPane.showMessageDialog(null, "Erro ao Gravar no Arquivo desejado" + Erro);
					}

				}
			}
		});

		btnConsultar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection connection = new ConnectionFactory().getConnection();

				try {
					String valorCodigo = JOptionPane.showInputDialog("Insira o Código");
					PreparedStatement stmt = connection
							.prepareStatement("select * from cadastroAluno where id = " + valorCodigo);

					ResultSet rs = stmt.executeQuery();

					while (rs.next()) {
						tfCodigo.setText(rs.getString("id"));
						tfNome.setText(rs.getString("nome"));
						tfEmail.setText(rs.getString("email"));
						listaDasCidades.setSelectedItem(rs.getString("cidade"));
						tfBairro.setText(rs.getString("bairro"));
						listaCursos.setSelectedItem(rs.getString("curso"));
						if (RadioBtnSim.getText().equals(rs.getString("knew"))) {
							RadioBtnSim.setSelected(true);
						}
						if (RadioBtnNao.getText().equals(rs.getString("knew"))) {
							RadioBtnNao.setSelected(true);
						}
					}
					stmt.close();
					rs.close();

					btnAlterar.setEnabled(true);

				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, "Erro durante a consulta! \n" + erro);
				}

			}
		});

		btnAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection connection = new ConnectionFactory().getConnection();
				if (tfNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O Nome não pode estar em branco!");
					tfNome.requestFocus();
				} else if (tfEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O Email não pode estar em branco!");
					tfEmail.requestFocus();
				} else if (listaCursos.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha o curso que deseja fazer");
					listaCursos.requestFocus();
				} else {

					String sql = "update cadastroaluno set nome=?, email=?, cidade=?, bairro=?, curso=?, knew=? where id= "
							+ tfCodigo.getText();
					try {
						PreparedStatement stmt = connection.prepareStatement(sql);
						stmt.setString(1, tfNome.getText());
						stmt.setString(2, tfEmail.getText());
						stmt.setString(3, (String) listaDasCidades.getSelectedItem());
						stmt.setString(4, tfBairro.getText());
						stmt.setString(5, (String) listaCursos.getSelectedItem());
						if (RadioBtnSim.isSelected()) {
							stmt.setString(6, RadioBtnSim.getText());
						}
						if (RadioBtnNao.isSelected()) {
							stmt.setString(6, RadioBtnNao.getText());
						}
						stmt.execute();
						stmt.close();

						String a = restore(tfEmail.getText());

						JOptionPane.showMessageDialog(null,
								"Arquivo Alterado !\nAluno(a) matriculado(a) com registro: " + a);

						tfCodigo.setText("");
						tfNome.setText("");
						tfEmail.setText("");
						listaDasCidades.setSelectedItem(null);
						tfBairro.setText("");
						listaCursos.setSelectedItem(null);
						grupo.clearSelection();

					} catch (Exception Erro) {
						JOptionPane.showMessageDialog(null, "Erro ao Gravar no Arquivo" + Erro);
					}

				}
			}
		});

		itemMenuEditar2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection connection = new ConnectionFactory().getConnection();

				try {
					PreparedStatement stmt = connection
							.prepareStatement("delete from cadastroaluno where id = " + tfCodigo.getText());

					stmt.execute();
					stmt.close();
					JOptionPane.showMessageDialog(null, "Arquivo deletado");

					tfCodigo.setText("");
					tfNome.setText("");
					tfEmail.setText("");
					listaDasCidades.setSelectedItem(null);
					tfBairro.setText("");
					listaCursos.setSelectedItem(null);
					grupo.clearSelection();

				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, "Erro durante comunicação com o banco de dados \n" + erro);
				}

			}
		});
	}
}
