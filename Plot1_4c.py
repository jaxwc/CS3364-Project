import matplotlib.pyplot as plt
import pandas as pd

df = pd.read_csv("benchmark_sorted.csv")

best = df.loc[df.groupby("n")["avg_time_ms"].idxmin()]

plt.figure(figsize=(8, 5))
plt.plot(best["n"], best["k"], marker="o", linewidth=2)
plt.xlabel("n (array size)")
plt.ylabel("Optimal K")
plt.title("Optimal K vs n (sorted input)")
plt.grid(True, alpha=0.3)
plt.savefig("deliverable1_4c.png", dpi=150, bbox_inches="tight")
plt.show()

print(best[["n", "k", "avg_time_ms"]])
