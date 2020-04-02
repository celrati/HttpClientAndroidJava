const express = require("express");


const router = express.Router();


router.post(
  "",
  (req, res, next) => {

});


router.put( "/:id", (req, res, next) => {
    console.log("PUT");
});


router.get("", (req, res, next) => {
    console.log("GET");
});

router.get("/:id", (req, res, next) => {
    console.log("GET with id");
});

router.delete("/:id", (req, res, next) => {
    console.log("DELETE");
});

module.exports = router;
