import java.awt.{Color, Dimension, Graphics}
import javax.swing.*

object TrianglesPanel extends JPanel:

  setBackground(Color.white)

  extension (p: Point)
    def deviceCoords(panelHeight: Int): (Int, Int) =
      (Math.round(p.x), panelHeight - Math.round(p.y))

  override def paintComponent(g: Graphics): Unit =

    super.paintComponent(g)

    val panelSize: Dimension = getSize()
    val panelWidth = panelSize.width - 1
    val panelHeight = panelSize.height - 1
    val panelCentre = Point(panelWidth / 2, panelHeight / 2)
    val triangleSide: Float = 0.95F * Math.min(panelWidth, panelHeight)
    val triangleHeight: Float = (0.5F * triangleSide) * Math.sqrt(3).toFloat

    val shrinkAndTwist: Triangle => Triangle =
      val q = 0.05F
      val p = 1 - q
      def combine(a: Point, b: Point) = Point(p * a.x + q * b.x, p * a.y + q * b.y)
      { case Triangle(a,b,c) => Triangle(combine(a,b), combine(b,c), combine(c,a)) }

    def drawLine(a: Point, b: Point): Unit =
      val (ax,ay) = a.deviceCoords(panelHeight)
      val (bx,by) = b.deviceCoords(panelHeight)
      g.drawLine(ax, ay, bx, by)

    val draw: Triangle => Unit =
      case Triangle(a, b, c) =>
        drawLine(a, b)
        drawLine(b, c)
        drawLine(c, a)

    val triangle = Triangle(panelCentre, triangleSide, triangleHeight)

    LazyList.iterate(triangle)(shrinkAndTwist).take(50).foreach(draw)