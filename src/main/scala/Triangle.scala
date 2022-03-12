case class Triangle(a: Point, b: Point, c: Point)

object Triangle:
  
  def apply(centre: Point, side: Float, height: Float): Triangle =
    val Point(x,y) = centre
    val halfSide = 0.5F * side
    val bottomLeft = Point(x - halfSide, y - 0.5F * height)
    val bottomRight = Point(x + halfSide, y - 0.5F * height)
    val top = Point(x, y + 0.5F * height )
    Triangle(bottomLeft,bottomRight,top)