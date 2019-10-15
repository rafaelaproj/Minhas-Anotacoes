package rafaelacs.com.br.exminhasanotacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import rafaelacs.com.br.exminhasanotacoes.database.BancoDeDados;

public class CreateNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);
    }

    public void back(View v) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    public void createNote(View v) {
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        EditText titulo = (EditText)findViewById(R.id.tituloText);
        EditText conteudo = (EditText)findViewById(R.id.conteudoText);
        boolean resultado = bancoDeDados.createNote(titulo.getText().toString(), conteudo.getText().toString());

        if(resultado) {
            Toast.makeText(getApplicationContext(), "Anotação criada com sucesso!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Infelizmente ocorreu um erro, tente novamente.", Toast.LENGTH_LONG).show();
        }

        back(v);
    }
}
