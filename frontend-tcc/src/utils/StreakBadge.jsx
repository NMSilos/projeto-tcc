import fire from "../assets/fire.png";

export function buscarStreakBadge(streaks) {
    if (!streaks || streaks <= 0) return null;


  return {
    icon: <img src={fire} className="emoji-badge" alt="Fogo" />,
    label: `${streaks} dia(s) lido(s)`,
    variant: "streak"
  };
}
