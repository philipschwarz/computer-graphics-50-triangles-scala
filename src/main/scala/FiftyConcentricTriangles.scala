import javax.swing.{JFrame, SwingUtilities, WindowConstants}

@main def main: Unit =
  // Create the frame/panel on the event dispatching thread.
  SwingUtilities.invokeLater(
    new Runnable():
      def run: Unit = drawTriangles
  )

def drawTriangles: Unit =
  JFrame.setDefaultLookAndFeelDecorated(true)
  val frame = new JFrame("Triangles: 50 triangles inside each other")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  frame.setSize(600, 400)
  frame.add(TrianglesPanel)
  frame.setVisible(true)
