import java.awt.{Color, Dimension, Graphics}
import javax.swing.*

@main def main: Unit =
  // Create the frame on the event dispatching thread.
  SwingUtilities.invokeLater(
    new Runnable():
      def run: Unit = Triangles()
  )

class Triangles:
  JFrame.setDefaultLookAndFeelDecorated(true)
  val frame = new JFrame("Triangles: 50 triangles inside each other")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  frame.setSize(600, 400)
  frame.add(TrianglesPanel())
  frame.setVisible(true)

class TrianglesPanel extends JPanel:

  setBackground(Color.white)

  var maxX, maxY, minMaxXY, xCenter, yCenter = 0

  def initgr: Unit = {
    val d: Dimension = getSize() 
    maxX = d.width - 1 
    maxY = d.height - 1 
    minMaxXY = Math.min(maxX, maxY) 
    xCenter = maxX / 2 
    yCenter = maxY / 2 
  }

  def iX(x: Float) = Math.round(x)
  def iY(y: Float) = maxY - Math.round(y)

  override def paintComponent(g: Graphics): Unit =

    super.paintComponent(g)

    initgr

    val side: Float = 0.95F * minMaxXY
    val sideHalf: Float = 0.5F * side
    val h: Float = sideHalf * Math.sqrt(3).toFloat
    val q: Float = 0.05F 
    val p: Float = 1 - q 

    var xA, yA, xB, yB, xC, yC = 0F
    var xA1, yA1, xB1, yB1, xC1, yC1 = 0F

    xA = xCenter - sideHalf 
    yA = yCenter - 0.5F * h 
    xB = xCenter + sideHalf 
    yB = yA 
    xC = xCenter 
    yC = yCenter + 0.5F * h 

    for (i <- 1 to 50)

      g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB)) 
      g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC)) 
      g.drawLine(iX(xC), iY(yC), iX(xA), iY(yA)) 

      xA1 = p * xA + q * xB 
      yA1 = p * yA + q * yB 
      xB1 = p * xB + q * xC 
      yB1 = p * yB + q * yC 
      xC1 = p * xC + q * xA 
      yC1 = p * yC + q * yA 

      xA = xA1;  xB = xB1;  xC = xC1
      yA = yA1;  yB = yB1;  yC = yC1