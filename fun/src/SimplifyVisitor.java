class SimplifyVisitor implements Visitor {
  public Object visit(Exp e) {
  	return e.accept(this);
  }
  public Object visit(Plus e) {
  	Exp a = (Exp)e.e1.accept(this);
  	Exp b = (Exp)e.e2.accept(this);
  	if (a instanceof Num) {
  	  if (((Num)a).n == 0)
  	  	a = null;
  	}
  	if (b instanceof Num) {
  	  if (((Num)b).n == 0)
  	  	b = null;
  	}
  	if (a == null && b == null)
  	  return new Num(0);
  	if (a == null)
  	  return b;
  	if (b == null)
  	  return a;

  	return new Plus(a, b);
  }
  public Object visit(Minus e) {
  	Exp a = (Exp)e.e1.accept(this);
  	Exp b = (Exp)e.e2.accept(this);
  	if (a instanceof Num && b instanceof Num)
  	  return new Num(((Num)a).n - ((Num)b).n);
  	if (a instanceof Num) {
  	  if (((Num)a).n == 0)
  	  	a = null;
  	}
  	if (b instanceof Num) {
  	  if (((Num)b).n == 0)
  	  	b = null;
  	}
  	if (a == null && b == null)
  	  return new Num(0);
  	if (a == null)
  	  return new Times(new Num(-1), b);
  	if (b == null)
  	  return a;

  	return new Minus(a, b);
  }
  public Object visit(Times e) {
  	Exp a = (Exp)e.e1.accept(this);
  	Exp b = (Exp)e.e2.accept(this);
  	if (a instanceof Num) {
  	  if (((Num)a).n == 0)
  	  	return new Num(0);
  	  if (((Num)a).n == 1)
  	  	a = null;
  	}
  	if (b instanceof Num) {
  	  if (((Num)b).n == 0)
  	  	return new Num(0);
  	  if (((Num)b).n == 1)
  	  	b = null;
  	}
  	if (a == null && b == null)
  	  return new Num(1);
  	if (a == null)
  	  return b;
  	if (b == null)
  	  return a;

  	return new Times(a, b);
  }
  public Object visit(Divide e) {
  	Exp a = (Exp)e.e1.accept(this);
  	Exp b = (Exp)e.e2.accept(this);
  	if (a instanceof Num) {
  	  if (((Num)a).n == 0)
  	  	return new Num(0);
  	}
  	if (b instanceof Num) {
  	  if (((Num)b).n == 1)
  	  	return a;
  	}
  	if (a instanceof Num && b instanceof Num)
  	  return simplify(((Num)a).n, ((Num)b).n);

  	return new Divide(a, b);
  }
  
  public Object simplify(double a, double b) {
  	if (a == b)
  	  return new Num(1);
  	if ((int)a == a && (int)b == b) {
  	  int x = (int)a;
  	  int y = (int)b;
  	  for (int i=Math.abs(Math.min(x,y)); i>1; i--)
  	  	if (x%i==0 && y%i==0)
  	  	  return new Divide(new Num(x/i), new Num(y/i));
  	}
  	return new Divide(new Num(a), new Num(b));
  }
  public Object visit(Power e) {
  	Exp a = (Exp)e.e1.accept(this);
  	Exp b = (Exp)e.e2.accept(this);
  	if (a instanceof Num) {
  	  if (((Num)a).n == 0)
  	  	return new Num(0);
  	  if (((Num)a).n == 1)
  	  	return new Num(1);;
  	}
  	if (b instanceof Num) {
  	  if (((Num)b).n == 0)
  	  	return new Num(1);
  	  if (((Num)b).n == 1)
  	  	return a;
  	}

  	return new Power(a, b);
  }
  public Object visit(Var e) {
  	return e;
  }
  public Object visit(Num e) {
  	return e;
  }
  public Object visit(Sin e) {
  	return e;
  }
  public Object visit(Cos e) {
  	return e;
  }
  public Object visit(Tan e) {
  	return e;
  }
  public Object visit(Ln e) {
  	return e;
  }
  public Object visit(Log e) {
  	return e;
  }
  public Object visit(Euler e) {
  	return e;
  }
  public Object visit(Abs e) {
  	return e;
  }
}

