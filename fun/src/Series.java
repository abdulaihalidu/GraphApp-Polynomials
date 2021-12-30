class Series {
 //Exp ast;
 String id;
 public static void main(String args[]) {
  try {
    Exp e = new FuncParser(System.in).Start();
    boolean firstpass = true;
    for (int i=0; i<6; i++) {
      double d = ((Double)deriveNEval(e, i, 0)).doubleValue();
      if (d == 0)
      	continue;
      //Exp e2 = new Divide(new Times(new Num(d), new Power(new Var("x"), new Num(i))), new Num(fact(i)));
      Exp e2 = new Times(new Divide(new Num(d), new Num(fact(i))), new Power(new Var("x"), new Num(i)));
      e2 = (Exp)new SimplifyVisitor().visit(e2);
      String str = (String)new PrintVisitor().visit(e2);
      
      System.out.print((firstpass ? "" : "+") + str);
      firstpass = false;
    }
  }
  catch (Exception en) {
    System.out.println("Syntax error!");
    System.out.println(en.getMessage());
   }
  }
  
  public static Object deriveNEval(Exp e, int n, int x) {
  	for (int i=0; i<n; i++)
  	  e = (Exp)new DeriveVisitor("x").visit(e);
  	return new EvalVisitor(x).visit(e);
  }
  
  public static long fact(int n) {
  	return n < 2 ? 1 : n * fact(n-1);
  }
}
