@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.math.acos
import kotlin.math.sqrt

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = sqrt(this.dx * this.dx + this.dy * this.dy)

  val radiant: Double
    get() {
      val rad: Double = acos(this.dx/this.magnitude)
      return if (this.dy < 0) (-rad)
      else rad
    }

  val degree: Double
    get() = radiant * (180.0 / Math.PI)

  val unit: Vector2D
    get() = Vector2D(this.dx / magnitude, this.dy / magnitude)

  val normal: Vector2D
    get() = Vector2D(this.dy / magnitude, -this.dx / magnitude)

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(scalar * this.dx, scalar * this.dy)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(this.dx / scalar, this.dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return (this.dx * v.dx + this.dy * v.dy)
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(this.dx + v.dx, this.dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(this.dx + p.x, this.dy + p.y)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(-this.dx, -this.dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(this.dx - v.dx, this.dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return (this * target.unit)
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return (this.scalarProject(target) * target.unit)
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return (v * this)
}
