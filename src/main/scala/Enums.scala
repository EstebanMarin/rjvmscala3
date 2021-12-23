object Enums:
  enum Permissions:
    case READ, WRITE, EXECUTE, NONE
    def openDocument(): Unit =
      if (this == READ) println("opening document ... ")
      else println("reading document")

  val somePermissions: Permissions = Permissions.READ

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

@main def enumsMain =
  println("Hello from enums")
