
abstract class Exp {
  public abstract double eval();
  public abstract String tree();
  public abstract Object accept(Visitor v);
}

class Plus extends Exp {
  Exp e1, e2;
  public Plus(Exp a, Exp b) {
  	e1 = a;
  	e2 = b;
  }
  public double eval() {
  	return e1.eval() + e2.eval();
  }
  public String tree() {
  	return "new Plus(" + e1.tree() + ", " + e2.tree() + ")";
  }
  public String toString() {
  	return e1 + " " + e2 + " +";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Minus extends Exp {
  Exp e1, e2;
  public Minus(Exp a, Exp b) {
  	e1 = a;
  	e2 = b;
  }
  public double eval() {
  	return e1.eval() - e2.eval();
  }
  public String tree() {
  	return "new Minus(" + e1.tree() + ", " + e2.tree() + ")";
  }
  public String toString() {
  	return e1 + " " + e2 + " -";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Times extends Exp {
  Exp e1, e2;
  public Times(Exp a, Exp b) {
  	e1 = a;
  	e2 = b;
  }
  public double eval() {
  	return e1.eval() * e2.eval();
  }
  public String tree() {
  	return "new Times(" + e1.tree() + ", " + e2.tree() + ")";
  }
  public String toString() {
  	return e1 + " " + e2 + " *";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Divide extends Exp {
  Exp e1, e2;
  public Divide(Exp a, Exp b) {
  	e1 = a;
  	e2 = b;
  }
  public double eval() {
  	return e1.eval() / e2.eval();
  }
  public String tree() {
  	return "new Divide(" + e1.tree() + ", " + e2.tree() + ")";
  }
  public String toString() {
  	return e1 + " " + e2 + " /";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Power extends Exp {
  Exp e1, e2;
  public Power(Exp a, Exp b) {
  	e1 = a;
  	e2 = b;
  }
  public double eval() {
  	return Math.pow(e1.eval(), e2.eval());
  }
  public String tree() {
  	return "new Power(" + e1.tree() + ", " + e2.tree() + ")";
  }
  public String toString() {
  	return e1 + " " + e2 + " ^";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Var extends Exp {
  String id;
  public Var(String a) {
  	id = a;
  }
  public double eval() {
  	return 0.0;
  }
  public String tree() {
  	return "new Var(\"" + id + "\")";
  }
  public String toString() {
  	return id;
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Num extends Exp {
  double n;
  public Num(double a) {
  	n = a;
  }
  public double eval() {
  	return n;
  }
  public String tree() {
  	return "new Num(" + n + ")";
  }
  public String toString() {
  	return n + "";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Sin extends Exp {
  Exp e;
  public Sin(Exp a) {
  	e = a;
  }
  public double eval() {
  	return Math.sin(e.eval());
  }
  public String tree() {
  	return "new Sin(" + e.tree() + ")";
  }
  public String toString() {
  	return e + " sin";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Cos extends Exp {
  Exp e;
  public Cos(Exp a) {
  	e = a;
  }
  public double eval() {
  	return Math.cos(e.eval());
  }
  public String tree() {
  	return "new Cos(" + e.tree() + ")";
  }
  public String toString() {
  	return e + " cos";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Tan extends Exp {
  Exp e;
  public Tan(Exp a) {
  	e = a;
  }
  public double eval() {
  	return Math.tan(e.eval());
  }
  public String tree() {
  	return "new Tan(" + e.tree() + ")";
  }
  public String toString() {
  	return e + " tan";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Ln extends Exp {
  Exp e;
  public Ln(Exp a) {
  	e = a;
  }
  public double eval() {
  	return Math.log(e.eval());
  }
  public String tree() {
  	return "new Ln(" + e.tree() + ")";
  }
  public String toString() {
  	return e + " ln";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Log extends Exp {
  Exp e;
  public Log(Exp a) {
  	e = a;
  }
  public double eval() {
  	return Math.log10(e.eval());
  }
  public String tree() {
  	return "new Log(" + e.tree() + ")";
  }
  public String toString() {
  	return e + " log";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Euler extends Exp {
  Exp e;
  public Euler(Exp a) {
  	e = a;
  }
  public double eval() {
  	return Math.exp(e.eval());
  }
  public String tree() {
  	return "new Euler(" + e.tree() + ")";
  }
  public String toString() {
  	return e + " exp";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}

class Abs extends Exp {
  Exp e;
  public Abs(Exp a) {
  	e = a;
  }
  public double eval() {
  	return Math.abs(e.eval());
  }
  public String tree() {
  	return "new Abs(" + e.tree() + ")";
  }
  public String toString() {
  	return e + " abs";
  }
  public Object accept(Visitor v) {
  	return v.visit(this);
  }
}
