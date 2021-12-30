class EvalVisitor implements Visitor {
 private double x;
 public static void main(String args[]) {
  try {
  	if (args.length < 1) {
  		System.out.println("Usage: java EvalVisitor number");
  	  return;
  	}
    Exp e = new FuncParser(System.in).Start();
    System.out.println("Result: " + new EvalVisitor(Double.parseDouble(args[0])).visit(e));
  }
  catch (Exception en) {
    System.out.println("Syntax error!");
    System.out.println(en.getMessage());
   }
  }
  public EvalVisitor(double a) {
  	x = a;
  }
  public Object visit(Exp e) {
  	return e.accept(this);
  }
  public Object visit(Plus e) {
  	Object a = e.e1.accept(this);
  	Object b = e.e2.accept(this);
  	double val = ((Double)a).doubleValue() + ((Double)b).doubleValue();
  	return new Double(val);
  }
  public Object visit(Minus e) {
  	Object a = e.e1.accept(this);
  	Object b = e.e2.accept(this);
  	double val = ((Double)a).doubleValue() - ((Double)b).doubleValue();
  	return new Double(val);
  }
  public Object visit(Times e) {
  	Object a = e.e1.accept(this);
  	Object b = e.e2.accept(this);
  	double val = ((Double)a).doubleValue() * ((Double)b).doubleValue();
  	return new Double(val);
  }
  public Object visit(Divide e) {
  	Object a = e.e1.accept(this);
  	Object b = e.e2.accept(this);
  	double val = ((Double)a).doubleValue() / ((Double)b).doubleValue();
  	return new Double(val);
  }
  public Object visit(Power e) {
  	Object a = e.e1.accept(this);
  	Object b = e.e2.accept(this);
  	double val = Math.pow(((Double)a).doubleValue(), ((Double)b).doubleValue());
  	return new Double(val);
  }
  public Object visit(Var e) {
  	return new Double(x);
  }
  public Object visit(Num e) {
  	return new Double(e.n);
  }
  public Object visit(Sin e) {
  	Object a = e.e.accept(this);
  	double val = Math.sin(((Double)a).doubleValue());
  	return new Double(val);
  }
  public Object visit(Cos e) {
  	Object a = e.e.accept(this);
  	double val = Math.cos(((Double)a).doubleValue());
  	return new Double(val);
  }
  public Object visit(Tan e) {
  	Object a = e.e.accept(this);
  	double val = Math.tan(((Double)a).doubleValue());
  	return new Double(val);
  }
  public Object visit(Ln e) {
  	Object a = e.e.accept(this);
  	double val = Math.log(((Double)a).doubleValue());
  	return new Double(val);
  }
  public Object visit(Log e) {
  	Object a = e.e.accept(this);
  	double val = Math.log10(((Double)a).doubleValue());
  	return new Double(val);
  }
  public Object visit(Euler e) {
  	Object a = e.e.accept(this);
  	double val = Math.exp(((Double)a).doubleValue());
  	return new Double(val);
  }
  public Object visit(Abs e) {
  	Object a = e.e.accept(this);
  	double val = Math.abs(((Double)a).doubleValue());
  	return new Double(val);
  }
}

