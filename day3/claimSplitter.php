<?php

$claim = "#1 @ 342,645: 25x20";

$splitClaim = preg_split("/(@|:)/", $claim);

$coordinates = trim($splitClaim[1]);
$splitCoords = explode(",", $coordinates);

$dimensions = trim($splitClaim[2]);
$splitDimensions = explode("x", $dimensions);

$claimObj = [];

$claimObj["id"] = trim($splitClaim[0]);
$claimObj["coords"] = [];
$claimObj["coords"]["x"] = $splitCoords[0];
$claimObj["coords"]["y"] = $splitCoords[1];
$claimObj["dimensions"] = [];
$claimObj["dimensions"]["x"] = $splitDimensions[0];
$claimObj["dimensions"]["y"] = $splitDimensions[1];


print_r($claimObj);

?>