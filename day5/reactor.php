<?php
ini_set('memory_limit','512M');

$file = fopen("./polymer.txt", "r");
$polymer = fgets($file);
$polyArr = str_split($polymer);
$startingArrayLength = count($polyArr);
$endingArrayLength = recReactor($polyArr, 0, 0);

echo "Finished operation. Ending array length was " . $endingArrayLength . ". Started with: " . $startingArrayLength;

function recReactor($polymerArray, $iteration, $reactionCount) {
    echo "Iteration: " . $iteration . "\n";
    if ($iteration == count($polymerArray)) {
        return $iteration;
    }

    $firstUnit = $polymerArray[$iteration];
    $nextUnit = $polymerArray[$iteration + 1];
    if ((ctype_upper($firstUnit) && $firstUnit == ucfirst($nextUnit)) || (!ctype_upper($firstUnit) && $firstUnit == lcfirst($nextUnit))) {
        array_splice($polymerArray, $iteration, 2);
        echo "REACTION! " . $firstUnit . " and " . $nextUnit . "\n";
        $iteration - 2;
        $reactionCount++;
    }
    return recReactor($polymerArray, $iteration + 1, $reactionCount);
}


?>