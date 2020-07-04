

class Rational(x: Int, y: Int) {
  def num = x
  def den = y
  def neg = new Rational(-num, den)
  override def toString = num + "/" + den
  def add(l: Rational): Rational = {
    new Rational(
      l.num*den + num*l.den,
      l.den*den
    )
  }
  def sub(l: Rational): Rational = add(l.neg)
  def mul(l: Rational): Rational = {
    new Rational(
      l.num*num,
      l.den*den
    )
  }
}

def addRat(l: Rational, r:Rational): Rational = {
  new Rational(l.num*r.den + r.num*l.den, l.den*r.den)
}

def makeString(r: Rational): String = {
  r.num + "/" + r.den
}

var r = new Rational(3,4)
r.num
r.den
var l = new Rational(1,2)
var sum = addRat(l,r)
sum.neg
l.add(r)
l.sub(r)
l.mul(r)
