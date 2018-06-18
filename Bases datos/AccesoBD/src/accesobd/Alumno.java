package accesobd;
public class Alumno{
    private String nome;
    private String dni;
    private int idade;
    private float nota;
    private boolean repite;
    
    Alumno(String n, String d, int i, float cal, boolean r) {
        if (n.length() > 10) {
            nome = n.substring(0, 10);
        } else {
            nome = n;
        }
        
        dni = d;
        idade = i;
        nota = cal;
        repite = r;
    }

    public String getNome() {
        return nome;
    }

    public String getDni() {
        return dni;
    }

    public int getIdade() {
        return idade;
    }

    public float getNota() {
        return nota;
    }

    public boolean isRepite() {
        return repite;
    }
    
    @Override
    public String toString() {
       return nome+"\t"+dni+"\t"+idade+"\t"+nota+"\t"+repite;
    }
}