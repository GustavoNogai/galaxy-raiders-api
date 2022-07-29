package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

class Explosion(initialPosition: Point2D, radius: Double) :
  SpaceObject("Explosion", 'X', initialPosition, Vector2D(0.0, 0.0), radius, 1.0){

  var lifespan: Int = 30

  fun render() {
    this.lifespan -= 1
  }

  fun died(): Boolean {
    return (this.lifespan <= 0)
  }
}