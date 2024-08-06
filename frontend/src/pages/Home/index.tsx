import React from "react";
import "./styles.css";
import { Link } from "react-router-dom";

function Home() {
  return (
    <div className="home-container">
      <div className="home-tittle">
        <h3>Venha adotar seu animalzinho!</h3>
        <Link to="/catalog">
          <button className="btn">Pets</button>
        </Link>
      </div>
    </div>
  );
}

export default Home;
