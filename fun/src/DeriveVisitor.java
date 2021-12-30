class DeriveVisitor implements Visitor {
 //Exp ast;
 String id;
 public static void main(String args[]) {
  try {
  	if (args.length < 1) {
  		System.out.println("Usage: java DeriveVisitor id");
  	  return;
  	}

    Exp e = new FuncParser(System.in).Start();
    DeriveVisitor drv = new DeriveVisitor(args[0]);
    Exp e2 = (Exp)drv.visit(e);
    Exp e3 = (Exp)(new SimplifyVisitor().visit(e2));
    String str = (String)(new PrintVisitor().visit(e3));
    System.out.println(str);
  }
  catch (Exception en) {
    System.out.println("Syntax error!");
    System.out.println(en.getMessage());
   }
  }
  public DeriveVisitor(String a) {
  	id = a;
  }
  public static Object deriveNEval(Exp e, int n, int x) {
  	for (int i=0; i<n; i++)
  	  e = (Exp)new DeriveVisitor("x").visit(e);
  	return new EvalVisitor(x).visit(e);
  }
  public Object visit(Exp e) {
  	return e.accept(this);
  }
  public Object visit(Plus e) {
  	Exp a = (Exp)e.e1.accept(this);
  	Exp b = (Exp)e.e2.accept(this);
  	return new Plus(a, b);
  }
  public Object visit(Minus e) {
  	Exp a = (Exp)e.e1.accept(this);
  	Exp b = (Exp)e.e2.accept(this);
  	return new Minus(a, b);
  }
  public Object visit(Times e) {
  	Exp a = (Exp)e.e1.accept(this);
  	Exp b = (Exp)e.e2.accept(this);
  	return new Plus(new Times(a, e.e2), new Times(e.e1, b));
  }
  public Object visit(Divide e) {
  	Exp a = (Exp)e.e1.accept(this);
  	Exp b = (Exp)e.e2.accept(this);
  	return new Divide(new Minus(new Times(a, e.e2), new Times(e.e1, b)), new Power(e.e2, new Num(2)));
  }
  public Object visit(Power e) {
  	Exp a = (Exp)e.e1.accept(this);
  	Exp b = (Exp)e.e2.accept(this);
  	return new Plus(new Times(new Times(b, e), new Ln(e.e1)),
  	                new Times(new Times(e.e2, a), new Power(e.e1, new Minus(e.e2, new Num(1)))));
  }
  public Object visit(Var e) {
  	if (e.id.equals(id))
  	  return new Num(1);
  	return new Num(0);
  }
  public Object visit(Num e) {
  	return new Num(0);
  }
  public Object visit(Sin e) {
  	Exp a = (Exp)e.e.accept(this);
  	return new Times(a, new Cos(e.e));
  }
  public Object visit(Cos e) {
  	Exp a = (Exp)e.e.accept(this);
  	return new Times(new Times(new Num(-1), a), new Sin(e.e));
  }
  public Object visit(Tan e) {
  	Exp a = (Exp)e.e.accept(this);
  	return new Plus(new Num(1), new Power(e, new Num(2)));
  }
  public Object visit(Ln e) {
  	Exp a = (Exp)e.e.accept(this);
  	return new Divide(a, e.e);
  }
  public Object visit(Log e) {
  	Exp a = (Exp)e.e.accept(this);
  	return new Divide(a, new Times(e.e, new Ln(new Num(10))));
  }
  public Object visit(Euler e) {
  	Exp a = (Exp)e.e.accept(this);
  	return new Times(a, e);
  }
  public Object visit(Abs e) {
  	Exp a = (Exp)e.e.accept(this);
  	return new Abs(a);
  }
}

