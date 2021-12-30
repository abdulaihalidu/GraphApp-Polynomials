import java.io.*;

class Func {
  public static void main(String args[]) {
    try {
      // ��le�ler: +, -, *, /, ^ (toplama, ��karma, �arpma, b�lme, �s alma)
      // Temel fonksiyonlar: sin(x), cos(x), tan(x), ln(x), log(x), exp(x), abs(x)
      // Ornek fonksiyon
      String str = "2*x^3+3*exp(x^2+1)*sin(x)-x*ln(abs(cos(x)))";

      // Stream donusumu
      InputStream stream = new ByteArrayInputStream(str.getBytes());

      // Nesne agaci donusumu
      Exp e = new FuncParser(stream).Start();

      // x=2 icin degerlendirme
      double a = ((Double) new EvalVisitor(3).visit(e)).doubleValue();
      System.out.println("Result: " + a);

      // Nesne agaci �zerinden turev alma
      e = (Exp) new DeriveVisitor("x").visit(e);

      // x=3 icin degerlendirme
      a = ((Double) new EvalVisitor(3).visit(e)).doubleValue();
      System.out.println("Result: " + a);
    } catch (Exception en) {
      System.out.println("Syntax error: " + en.getMessage());
    }
  }
}
