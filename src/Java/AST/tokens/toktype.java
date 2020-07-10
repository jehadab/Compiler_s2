package Java.AST.tokens;

import Java.AST.Node;
import org.antlr.v4.runtime.Token;

public class toktype extends Node {
  Token identifir;
      Token nul;
      Token NUMERIC_LITERA;
      Token ONE_CHAR_LETTER;

    public void setIdentifir(Token identifir) {
        this.identifir = identifir;
    }

    public void setNul(Token nul) {
        this.nul = nul;
    }

    public void setNUMERIC_LITERA(Token NUMERIC_LITERA) {
        this.NUMERIC_LITERA = NUMERIC_LITERA;
    }

    public void setONE_CHAR_LETTER(Token ONE_CHAR_LETTER) {
        this.ONE_CHAR_LETTER = ONE_CHAR_LETTER;
    }

    public Token getIdentifir() {
        return identifir;
    }

    public Token getNul() {
        return nul;
    }

    public Token getNUMERIC_LITERA() {
        return NUMERIC_LITERA;
    }

    public Token getONE_CHAR_LETTER() {
        return ONE_CHAR_LETTER;
    }

}
