package cokeaswater.cstore.catalog.application.port.`in`.usecase

import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationCommand
import cokeaswater.cstore.catalog.application.port.`in`.params.RefreshBrandCoordinationScoreCommand

interface CoordinationCommandCase {
    fun refreshSummaryCoordination(): Int

    fun refreshBrandCoordination(command: RefreshBrandCoordinationCommand): Int

    fun refreshBrandCoordinationScore(command : RefreshBrandCoordinationScoreCommand): Int
}