package me.emilia

data class Nine(val diskMap: List<Int>) {
    fun calculate(): Long {
        val initialDiskLayout = calculateInitialDiskLayout()
        println("Initial DiskLayout: $initialDiskLayout")
        val diskLayoutNoSpaces = removeSpacesFromLayout(initialDiskLayout)
        println("Disklayout without spaces: $diskLayoutNoSpaces")
        return diskLayoutNoSpaces.withIndex()
            .filter { (_, id) -> id != -1 }
            .sumOf { (index, id) -> index.toLong() * id.toLong() }
    }

    fun calculatePartTwo(): Long {
        val initialDiskLayout = calculateInitialDiskLayout()
        println("Initial DiskLayout: $initialDiskLayout")
        val diskLayoutNoSpaces = removeSpacesFromLayoutWholeFiles(initialDiskLayout)
        println("Disklayout without spaces: $diskLayoutNoSpaces")
        return diskLayoutNoSpaces.withIndex()
            .filter { (_, id) -> id != -1 }
            .sumOf { (index, id) -> index.toLong() * id.toLong() }
    }

    private fun removeSpacesFromLayoutWholeFiles(diskLayout: MutableList<Int>): List<Int> {
        var index = diskLayout.size - 1
        while (index >= 0) {
            while (index >= 0 && diskLayout[index] == -1) {
                index--
            }
            if (index >= 0) {
                // file goes from fileIndex+1 to index (inclusive)
                val id = diskLayout[index]
                var fileIndex = index
                while (fileIndex >= 0 && diskLayout[fileIndex] == id) {
                    fileIndex--
                }
                val size = index - fileIndex

                // find consecutive empty parts, of size 'size',
                // before index
                var findIndex = 0
                var currentSize = 0
                while (findIndex < index) {
                    if (diskLayout[findIndex] == -1) {
                        currentSize++
                    } else {
                        currentSize = 0
                    }
                    if (currentSize >= size) {
                        break
                    }
                    findIndex++
                }
                if (currentSize >= size) { // found a space long enough
                    val firstIndex = findIndex - size + 1
                    for (i in firstIndex until firstIndex + size) {
                        diskLayout[i] = id
                    }
                    for (i in fileIndex + 1 until index + 1) {
                        diskLayout[i] = -1
                    }
                } else {
                    // didn't find a space long enough
                    index -= size
                }
            }
        }


        return diskLayout
    }

    private fun calculateInitialDiskLayout(): MutableList<Int> {
        val disk = mutableListOf<Int>()
        var isSpace = false
        var currentIndex = 0
        for (size in diskMap) {
            val element = if (isSpace) -1  else currentIndex
            repeat(size) {
                disk.add(element)
            }

            if (!isSpace) {
                currentIndex++
            }
            isSpace = !isSpace
        }

        return disk
    }

    private fun removeSpacesFromLayout(diskLayout: MutableList<Int>): List<Int> {
        var leftIndex = 0
        var rightIndex = diskLayout.size - 1
        while (leftIndex <= rightIndex) {
            while (diskLayout[leftIndex] != -1) {
                leftIndex++
            }
            while (diskLayout[rightIndex] == -1) {
                rightIndex--
            }
            if (leftIndex <= rightIndex) {
                val temp = diskLayout[leftIndex]
                diskLayout[leftIndex] = diskLayout[rightIndex]
                diskLayout[rightIndex] = temp
            }
        }

        return diskLayout
    }
}
